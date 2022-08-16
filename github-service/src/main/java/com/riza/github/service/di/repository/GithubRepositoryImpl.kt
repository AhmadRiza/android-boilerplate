package com.riza.github.service.di.repository

import com.riza.github.common.entities.ErrorNetworkResult
import com.riza.github.common.entities.NetworkResult
import com.riza.github.common.model.Result
import com.riza.github.common.model.toEmptyResult
import com.riza.github.common.model.toErrorResult
import com.riza.github.network.mapper.safeApiCall
import com.riza.github.network.mapper.toNetworkResult
import com.riza.github.service.di.model.GithubSearchUser
import com.riza.github.service.di.model.GithubUserDetail
import com.riza.github.service.di.model.GithubUserRepo
import com.riza.github.service.di.model.asModel
import com.riza.github.service.di.source.remote.GithubService
import javax.inject.Inject

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubService
) : GithubRepository {
    override suspend fun searchUser(query: String, page: Int): Result<GithubSearchUser> {
        val result = safeApiCall {
            githubService.searchUser(query, page).toNetworkResult()
        }
        return when (result) {
            is NetworkResult.Success.WithData ->
                Result.Success.WithData(result.value.asModel())
            is ErrorNetworkResult -> result.toErrorResult()
            is NetworkResult.Success.EmptyData -> result.toEmptyResult()
        }
    }

    override suspend fun getUserDetail(login: String): Result<GithubUserDetail> {
        val result = safeApiCall {
            githubService.getUserDetail(login).toNetworkResult()
        }
        return when (result) {
            is NetworkResult.Success.WithData ->
                Result.Success.WithData(result.value.asModel())
            is ErrorNetworkResult -> result.toErrorResult()
            is NetworkResult.Success.EmptyData -> result.toEmptyResult()
        }
    }

    override suspend fun getUserRepositories(
        login: String,
        page: Int
    ): Result<List<GithubUserRepo>> {
        val result = safeApiCall {
            githubService.getUserRepos(login, page).toNetworkResult()
        }
        return when (result) {
            is NetworkResult.Success.WithData ->
                Result.Success.WithData(result.value.map { it.asModel() })
            is ErrorNetworkResult -> result.toErrorResult()
            is NetworkResult.Success.EmptyData -> result.toEmptyResult()
        }
    }
}
