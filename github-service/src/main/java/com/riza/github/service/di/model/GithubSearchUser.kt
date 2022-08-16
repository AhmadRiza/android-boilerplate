package com.riza.github.service.di.model

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

sealed interface GithubSearchUserResult

data class GithubSearchUser(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<GithubUser>
) : GithubSearchUserResult {
    data class GithubUser(
        val login: String,
        val id: Long,
        val avatarUrl: String
    )
}

object GithubSearchUserEmpty : GithubSearchUserResult

data class GithubSearchUserError(val message: String) : GithubSearchUserResult
