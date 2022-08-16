package com.riza.github.detail

import androidx.lifecycle.viewModelScope
import com.riza.github.common.base.BaseViewModel
import com.riza.github.common.di.IODispatcher
import com.riza.github.common.router.DetailIntentParam
import com.riza.github.detail.usecase.GetDetailDisplayProfile
import com.riza.github.detail.usecase.GetDetailDisplayProfile.Event
import com.riza.github.detail.usecase.GetDisplayUserRepos
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class DetailViewModel @Inject constructor(
    private val getDisplayUserRepos: GetDisplayUserRepos,
    private val getDetailDisplayProfile: GetDetailDisplayProfile,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel<DetailViewModel.Intent,
    DetailViewModel.State, DetailViewModel.Effect>(
    State()
) {

    sealed interface Intent {
        data class OnViewCreated(val param: DetailIntentParam) : Intent
        object OnLoadMoreRepos : Intent
        object OnBackPressed : Intent
        object RetryLoadRepos : Intent
    }

    data class State(
        val titleLabel: String = "",
        val displayItems: List<DetailDisplayItemModel> = emptyList()
    )

    sealed interface Effect {
        object FinishActivity : Effect
    }

    private lateinit var intentParam: DetailIntentParam
    private var page: Int = 1

    override fun onIntentReceived(intent: Intent) {
        when (intent) {
            is Intent.OnViewCreated -> onViewCreated(intent.param)
            Intent.OnBackPressed -> {
                setEffect(Effect.FinishActivity)
            }
            Intent.OnLoadMoreRepos -> {
                onLoadMoreRepos()
            }
            Intent.RetryLoadRepos -> onRetryLoadRepos()
        }
    }

    private fun onRetryLoadRepos() {
        viewModelScope.launch { loadRepository() }
    }

    private fun onLoadMoreRepos() {
        viewModelScope.launch {
            if (viewState.displayItems.lastOrNull() !is EndOfListRepoItemModel &&
                viewState.displayItems.lastOrNull() is DetailDisplaySuccessItemModel
            ) {
                page++
                loadRepository()
            }
        }
    }

    private fun onViewCreated(param: DetailIntentParam) {
        viewModelScope.launch {
            intentParam = param
            setState { copy(titleLabel = "${param.name}'s Repositories") }
            getDetailDisplayProfile(param)
                .flowOn(ioDispatcher)
                .collect { event ->
                    when (event) {
                        is Event.ShowProfileSection -> {
                            setState {
                                copy(
                                    displayItems = event.displayItems
                                )
                            }
                        }
                    }
                }
            loadRepository()
        }
    }

    private suspend fun loadRepository() {
        getDisplayUserRepos(
            GetDisplayUserRepos.Param(
                userAvatarUrl = intentParam.avatarUrl, login = intentParam.login, page = page
            )
        ).flowOn(ioDispatcher)
            .collect { event ->
                when (event) {
                    GetDisplayUserRepos.Event.RepoResultEmpty -> {
                        submitRepoDisplayItems(EmptyRepoItemModel)
                    }
                    is GetDisplayUserRepos.Event.RepoResultFound -> {
                        submitRepoDisplayItems(*event.displayItems.toTypedArray())
                    }
                    GetDisplayUserRepos.Event.ShowEndOfList -> {
                        submitRepoDisplayItems(EndOfListRepoItemModel)
                    }
                    is GetDisplayUserRepos.Event.ShowError -> {
                        submitRepoDisplayItems(DetailRepoErrorItemModel(event.message))
                    }
                    GetDisplayUserRepos.Event.ShowLoading ->
                        submitRepoDisplayItems(LoadingRepoItemModel)
                    GetDisplayUserRepos.Event.ShowLoadingMore -> {
                        submitRepoDisplayItems(LoadingMoreRepoItemModel)
                    }
                }
            }
    }

    private fun submitRepoDisplayItems(vararg items: DetailDisplayItemModel) {
        val displayItems = viewState.displayItems
            .filter { it is DetailDisplaySuccessItemModel }
            .toMutableList()
        displayItems.addAll(items)
        setState { copy(displayItems = displayItems) }
    }
}
