package com.riza.github.service.di.usecase

import com.riza.github.common.base.BaseUseCase
import com.riza.github.common.model.Result
import com.riza.github.service.di.GithubServiceScope
import com.riza.github.service.di.model.GithubSearchUserEmpty
import com.riza.github.service.di.model.GithubSearchUserError
import com.riza.github.service.di.model.GithubSearchUserResult
import com.riza.github.service.di.repository.GithubRepository
import javax.inject.Inject

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
@GithubServiceScope
class SearchGithubUser @Inject constructor(
    private val repository: GithubRepository
) : BaseUseCase<GithubSearchUserResult,
    SearchGithubUser.Param>() {

    data class Param(
        val query: String,
        val page: Int
    )

    override suspend fun build(params: Param?): GithubSearchUserResult {
        requireNotNull(params)
        return when (val result = repository.searchUser(query = params.query, page = params.page)) {
            is Result.Error -> GithubSearchUserError(result.errorMessage)
            is Result.Success.Empty -> GithubSearchUserEmpty
            is Result.Success.WithData -> {
                if (result.data.items.isEmpty()) GithubSearchUserEmpty
                else result.data
            }
        }
    }
}
