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
class GetGithubUserDetail @Inject constructor(
    private val repository: GithubRepository
) : BaseUseCase<GithubUserDetailResult,
    GetGithubUserDetail.Param>() {

    data class Param(
        val login: String
    )

    override suspend fun build(params: Param?): GithubUserDetailResult {
        requireNotNull(params)
        return when (val result = repository.getUserDetail(login = params.login)) {
            is Result.Error -> GithubUserDetailError(result.errorMessage)
            is Result.Success.Empty -> GithubUserDetailError("User not found")
            is Result.Success.WithData -> result.data
        }
    }
}
