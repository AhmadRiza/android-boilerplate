package com.riza.github.home

import androidx.lifecycle.viewModelScope
import com.riza.github.common.base.BaseViewModel
import com.riza.github.common.di.IODispatcher
import com.riza.github.common.router.DetailIntentParam
import com.riza.github.home.usecase.SearchAndDisplayGithubUser
import com.riza.github.home.usecase.SearchAndDisplayGithubUser.Event.*
import com.riza.github.service.di.model.GithubUserDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class MainViewModel @Inject constructor(
    private val searchAndDisplayGithubUser: SearchAndDisplayGithubUser,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): BaseViewModel<MainViewModel.Intent, MainViewModel.State, MainViewModel.Effect>(State()) {

    sealed interface Intent {
        object OnViewCreated: Intent
        data class OnSearchBarTextChanged(val text: String): Intent
        object OnLoadMore: Intent
        data class OnClickUser(val id: Long): Intent
        object RetrySearchUser: Intent
    }

    data class State (
        val displayItems: List<MainDisplayItemModel> = emptyList(),
        val query: String = "",
    )

    sealed interface Effect {
        data class GoToDetail(val detailIntentParam: DetailIntentParam): Effect
    }

    private var page: Int = 1
    private var searchResult = mutableListOf<GithubUserDetail>()
    private var searchJob: Job? = null

    override fun onIntentReceived(intent: Intent) {
        when(intent) {
            Intent.OnViewCreated -> onViewCreated()
            is Intent.OnSearchBarTextChanged -> onSearchBarTextChanged(intent.text)
            Intent.OnLoadMore -> onLoadMore()
            is Intent.OnClickUser -> onClickUser(intent.id)
            Intent.RetrySearchUser -> onRetry()
        }
    }

    private fun onRetry() {
        searchUser(viewState.query)
    }

    private fun onClickUser(id: Long) {
        searchResult.find { it.id == id }?.let {
            val detailIntentParam = DetailIntentParam(
                login = it.login,
                id = it.id,
                name = it.name,
                avatarUrl = it.avatarUrl,
                company = it.company,
                bio = it.bio,
                location = it.location,
                email = it.email,
                followers = it.followers,
                following = it.following
            )
            setEffect(Effect.GoToDetail(detailIntentParam))
        }
    }

    private fun onSearchBarTextChanged(text: String) {
        setState { copy(query = text) }
        page = 1
        searchResult.clear()
        searchUser(text)
    }

    private fun onLoadMore() {
        if(viewState.displayItems.lastOrNull() !is EndOfUsersListItemModel
            && viewState.displayItems.lastOrNull() is SuccessDisplayItem) {
            page++
            searchUser(viewState.query)
        }
    }

    private fun searchUser(query: String) {
        searchJob?.cancel()
        if(query.isEmpty()) {
            setState { copy(displayItems = emptyList()) }
            return
        }
        searchJob = viewModelScope.launch {
            val param = SearchAndDisplayGithubUser.Param(
                query = query, page = page
            )
            searchAndDisplayGithubUser(param)
                .cancellable()
                .flowOn(ioDispatcher)
                .collect { event ->
                    when(event) {
                        SearchResultEmpty -> {
                            val displayItems = listOf(EmptySearchResultInfoItemModel)
                            setState { copy(displayItems = displayItems) }
                        }
                        is SearchResultFound -> {
                            //filter only detail and divider
                            val displayItems = viewState.displayItems
                                .filter { it is SuccessDisplayItem }
                                .toMutableList()
                            displayItems.addAll(event.displayItems)
                            setState { copy(displayItems = displayItems) }
                            searchResult.addAll(event.result)
                        }
                        is ShowError -> {
                            val displayItems = viewState.displayItems
                                .filter { it is SuccessDisplayItem }
                                .toMutableList()
                            displayItems.add(ErrorSearchUserItemModel(event.message))
                            setState { copy(displayItems = displayItems) }
                        }
                        ShowLoading -> {
                            val displayItems = listOf(LoadingItemModel)
                            setState { copy(displayItems = displayItems) }
                        }
                        ShowLoadingMore -> {
                            val displayItems = viewState.displayItems
                                .filter { it is SuccessDisplayItem }
                                .toMutableList()
                            displayItems.add(LoadingMoreItemModel)
                            setState { copy(displayItems = displayItems) }
                        }
                        ShowEndOfList -> {
                            val displayItems = viewState.displayItems
                                .filter { it is SuccessDisplayItem }
                                .toMutableList()
                            displayItems.add(EndOfUsersListItemModel)
                            setState { copy(displayItems = displayItems) }
                        }
                    }
                }
        }
    }

    private fun onViewCreated() {

    }

}