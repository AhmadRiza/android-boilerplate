package com.riza.github.service.di.source.remote

import com.riza.github.service.di.PageLimit
import com.riza.github.service.di.entity.GithubSearchUserEntity
import com.riza.github.service.di.entity.GithubUserDetailEntity
import com.riza.github.service.di.entity.GithubUserRepoEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
interface GithubService {

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = PageLimit.SEARCH_USER_LIMIT,
    ): Response<GithubSearchUserEntity>

    @GET("users/{login}")
    suspend fun getUserDetail(
        @Path("login") login: String
    ): Response<GithubUserDetailEntity>

    @GET("users/{login}/repos")
    suspend fun getUserRepos(
        @Path("login") login: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = PageLimit.USER_REPOS_LIMIT,
    ): Response<List<GithubUserRepoEntity>>

}