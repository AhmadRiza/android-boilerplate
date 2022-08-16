package com.riza.github.home.usecase

import com.riza.github.common.base.BaseUseCase
import com.riza.github.common.di.IODispatcher
import com.riza.github.home.GithubUserItemModel
import com.riza.github.home.MainDisplayItemModel
import com.riza.github.home.UserDividerItemModel
import com.riza.github.service.di.model.GithubSearchUser
import com.riza.github.service.di.model.GithubSearchUserEmpty
import com.riza.github.service.di.model.GithubSearchUserError
import com.riza.github.service.di.model.GithubUserDetail
import com.riza.github.service.di.usecase.GetGithubUserDetail
import com.riza.github.service.di.usecase.SearchGithubUser
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class SearchAndDisplayGithubUser @Inject constructor(
    private val searchGithubUser: SearchGithubUser,
    private val getGithubUserDetail: GetGithubUserDetail,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseUseCase<Flow<SearchAndDisplayGithubUser.Event>, SearchAndDisplayGithubUser.Param>() {

    data class Param(
        val query: String,
        val page: Int
    )

    sealed interface Event {
        data class SearchResultFound(
            val result: List<GithubUserDetail>,
            val displayItems: List<MainDisplayItemModel>,
        ) : Event
        object SearchResultEmpty : Event
        object ShowLoading : Event
        object ShowLoadingMore : Event
        object ShowEndOfList : Event
        data class ShowError(val message: String) : Event
    }

    override suspend fun build(params: Param?): Flow<Event> {
        requireNotNull(params)
        return channelFlow {
            val isFirstPage = params.page == 1
            if (isFirstPage) {
                send(Event.ShowLoading)
            } else {
                send(Event.ShowLoadingMore)
            }
            when (
                val result =
                    searchGithubUser(SearchGithubUser.Param(params.query, params.page))
            ) {
                is GithubSearchUser -> {
                    val userDetailsCalls = result.items.map {
                        async {
                            getGithubUserDetail(GetGithubUserDetail.Param(it.login))
                        }
                    }
                    val userDetailResult = userDetailsCalls.awaitAll()
                    val userDetails: List<GithubUserDetail> =
                        userDetailResult.filterIsInstance<GithubUserDetail>()

                    val displayItems = mutableListOf<MainDisplayItemModel>()
                    userDetails.forEachIndexed { index, detail ->
                        if (index != 0 || !isFirstPage) displayItems.add(UserDividerItemModel)
                        displayItems.add(detail.toDisplayItem())
                    }

                    if (displayItems.isEmpty()) {
                        if (isFirstPage) {
                            send(Event.SearchResultEmpty)
                        } else {
                            send(Event.ShowEndOfList)
                        }
                    } else {
                        send(
                            Event.SearchResultFound(
                                result = userDetails,
                                displayItems = displayItems
                            )
                        )
                    }
                }
                GithubSearchUserEmpty -> {
                    if (isFirstPage) {
                        send(Event.SearchResultEmpty)
                    } else {
                        send(Event.ShowEndOfList)
                    }
                }
                is GithubSearchUserError -> send(Event.ShowError(result.message))
            }
        }
    }

    private fun GithubUserDetail.toDisplayItem() = GithubUserItemModel(
        name = name,
        userName = "@$login",
        avatarUrl = avatarUrl,
        description = bio + if (company.isNotEmpty()) " of $company" else "",
        address = location,
        email = email,
        id = id
    )
}
