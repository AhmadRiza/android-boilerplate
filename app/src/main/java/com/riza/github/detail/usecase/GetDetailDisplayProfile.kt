package com.riza.github.detail.usecase

import com.riza.github.common.base.BaseUseCase
import com.riza.github.common.number.NumberFormatter
import com.riza.github.common.router.DetailIntentParam
import com.riza.github.detail.DetailDisplayItemModel
import com.riza.github.detail.DetailProfileItemModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

class GetDetailDisplayProfile @Inject constructor(
    private val numberFormatter: NumberFormatter
) :
    BaseUseCase<Flow<GetDetailDisplayProfile.Event>, DetailIntentParam>() {

    sealed interface Event {
        data class ShowProfileSection(val displayItems: List<DetailDisplayItemModel>) : Event
    }

    override suspend fun build(params: DetailIntentParam?): Flow<Event> {
        requireNotNull(params)
        return flow {
            displayProfileSection(params)
        }
    }

    private suspend fun FlowCollector<Event>.displayProfileSection(params: DetailIntentParam) {
        val displayItems: List<DetailDisplayItemModel> = listOf(
            params.toProfileSection()
        )
        emit(Event.ShowProfileSection(displayItems))
    }

    private fun DetailIntentParam.toProfileSection() = DetailProfileItemModel(
        avatarUrl = avatarUrl,
        name = name,
        userName = login,
        description = bio + if (company.isNotEmpty()) " of $company" else "",
        followers = numberFormatter.prettyCount(followers.toLong()),
        following = numberFormatter.prettyCount(following.toLong()),
        address = location,
        email = email
    )
}
