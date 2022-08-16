package com.riza.github.service.di.repository

import com.riza.github.common.model.Result
import com.riza.github.service.di.model.GithubSearchUser
import com.riza.github.service.di.model.GithubUserDetail
import com.riza.github.service.di.model.GithubUserRepo

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
interface GithubRepository {
    suspend fun searchUser(
        query: String,
        page: Int
    ): Result<GithubSearchUser>

    suspend fun getUserDetail(
        login: String
    ): Result<GithubUserDetail>

    suspend fun getUserRepositories(
        login: String,
        page: Int
    ): Result<List<GithubUserRepo>>
}
