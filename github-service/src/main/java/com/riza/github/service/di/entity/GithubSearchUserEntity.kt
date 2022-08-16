package com.riza.github.service.di.entity

import androidx.annotation.Keep

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

@Keep
data class GithubSearchUserEntity(
    val totalCount: Int?,
    val incompleteResults: Boolean?,
    val items: List<GithubUserEntity?>
) {
    @Keep
    data class GithubUserEntity(
        val login: String?,
        val id: Long?,
        val avatarUrl: String?
    )
}
