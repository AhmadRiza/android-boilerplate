package com.riza.github.service.di.model

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
sealed interface GithubUserDetailResult
data class GithubUserDetailError(val message: String): GithubUserDetailResult
data class GithubUserDetail(
    val login: String,
    val id: Long,
    val name: String,
    val avatarUrl: String?,
    val company: String,
    val bio: String,
    val location: String,
    val email: String,
    val followers: Int,
    val following: Int,
): GithubUserDetailResult