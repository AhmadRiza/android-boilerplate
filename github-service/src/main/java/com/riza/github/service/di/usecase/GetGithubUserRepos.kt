package com.riza.github.service.di.usecase

import com.riza.github.common.base.BaseUseCase
import com.riza.github.common.model.Result
import com.riza.github.service.di.GithubServiceScope
import com.riza.github.service.di.model.*
import com.riza.github.service.di.repository.GithubRepository
import javax.inject.Inject

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
@GithubServiceScope
class GetGithubUserRepos @Inject constructor(
    private val repository: GithubRepository
) : BaseUseCase<GithubUserRepoResult,
    GetGithubUserRepos.Param>() {

    data class Param(
        val login: String,
        val page: Int
    )

    override suspend fun build(params: Param?): GithubUserRepoResult {
        requireNotNull(params)
        return when (val result = repository.getUserRepositories(login = params.login, page = params.page)) {
            is Result.Error -> GithubUserRepoError(result.errorMessage)
            is Result.Success.Empty -> GithubUserRepoEmpty
            is Result.Success.WithData -> {
                if (result.data.isEmpty()) GithubUserRepoEmpty
                else GithubUserRepoSuccess(result.data)
            }
        }
    }
}
