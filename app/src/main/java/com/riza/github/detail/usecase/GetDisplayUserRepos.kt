package com.riza.github.detail.usecase

import com.riza.github.common.base.BaseUseCase
import com.riza.github.common.date.DateFormat
import com.riza.github.common.date.DateFormatter
import com.riza.github.detail.DetailDisplayDividerItemModel
import com.riza.github.detail.DetailDisplayItemModel
import com.riza.github.detail.DetailRepoItemModel
import com.riza.github.home.MainDisplayItemModel
import com.riza.github.service.di.model.*
import com.riza.github.service.di.usecase.GetGithubUserRepos
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

class GetDisplayUserRepos @Inject constructor(
    private val getGithubUserRepos: GetGithubUserRepos,
    private val dateFormatter: DateFormatter
): BaseUseCase<Flow<GetDisplayUserRepos.Event>, GetDisplayUserRepos.Param>() {
    
    data class Param(val userAvatarUrl: String?, val login: String, val page: Int)
    
    sealed interface Event {
        data class RepoResultFound(
            val displayItems: List<DetailDisplayItemModel>,
        ) : Event
        object RepoResultEmpty : Event
        object ShowLoading : Event
        object ShowLoadingMore : Event
        object ShowEndOfList: Event
        data class ShowError(val message: String) : Event
    }
    
    override suspend fun build(params: Param?): Flow<Event> {
        requireNotNull(params)
        return channelFlow {
            getUserRepos(params.userAvatarUrl, params.login, params.page)
        }
    }

    private suspend fun ProducerScope<Event>.getUserRepos(
        avatarUrl: String?,
        login: String,
        page: Int
    ) {
        val isFirstPage = page == 1
        if (isFirstPage) {
            send(Event.ShowLoading)
        } else {
            send(Event.ShowLoadingMore)
        }
        
        when(val result = getGithubUserRepos(GetGithubUserRepos.Param(login, page))) {

            GithubUserRepoEmpty -> {
                if(isFirstPage) {
                    send(Event.RepoResultEmpty)
                } else {
                    send(Event.ShowEndOfList)
                }
            }
            is GithubUserRepoError -> send(Event.ShowError(result.message))
            is GithubUserRepoSuccess -> {
                val displayItems = mutableListOf<DetailDisplayItemModel>()
                result.repos.forEachIndexed { _, githubUserRepo ->
                    displayItems.add(DetailDisplayDividerItemModel)
                    displayItems.add(githubUserRepo.toRepoSection(avatarUrl))
                }
                send(Event.RepoResultFound(displayItems))
            }
        }
    }

    private fun GithubUserRepo.toRepoSection(avatarUrl: String?): DetailRepoItemModel {
        val lastUpdateDate = dateFormatter.getDateOrNull(updatedAt, DateFormat.ISO_TIMESTAMP)
        val lastUpdateLabel = if(lastUpdateDate != null) dateFormatter
            .getRelativelyFormattedDate(lastUpdateDate.time, DateFormat.DATE_ONLY) else ""

        return DetailRepoItemModel(
            avatarUrl = avatarUrl,
            name = name,
            description = description,
            starsCount = stargazersCount.toString(),
            lastUpdate = lastUpdateLabel
        )
    }

}