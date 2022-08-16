package com.riza.github.service.di.model

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
sealed interface GithubUserRepoResult

object GithubUserRepoEmpty : GithubUserRepoResult
data class GithubUserRepoError(val message: String) : GithubUserRepoResult
data class GithubUserRepoSuccess(val repos: List<GithubUserRepo>) : GithubUserRepoResult

data class GithubUserRepo(
    val name: String,
    val description: String,
    val stargazersCount: Int,
    val updatedAt: String
)
