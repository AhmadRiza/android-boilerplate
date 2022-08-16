package com.riza.github.service

import com.riza.github.common.model.DefaultErrorMessage
import com.riza.github.common.model.Result
import com.riza.github.service.di.model.GithubUserRepo
import com.riza.github.service.di.model.GithubUserRepoEmpty
import com.riza.github.service.di.model.GithubUserRepoError
import com.riza.github.service.di.model.GithubUserRepoSuccess
import com.riza.github.service.di.repository.GithubRepository
import com.riza.github.service.di.usecase.GetGithubUserRepos
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

/**
 * Install Kotest android studio plugin to run these
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class GetGithubUserReposTest : ShouldSpec() {
    private val repository: GithubRepository = mockk()
    private val getGithubUserRepos = GetGithubUserRepos(repository)

    init {
        val param = GetGithubUserRepos.Param(login = "Ahmad Riza", page = 1)
        context("getGithubUserRepos") {
            context("getSuccessful result") {
                context("with data") {
                    beforeTest {
                        coEvery {
                            repository.getUserRepositories(param.login, param.page)
                        } returns Result.Success.WithData(mockGithubUserRepos)
                    }
                    should("return GithubUserDetail") {
                        val expected = GithubUserRepoSuccess(mockGithubUserRepos)
                        getGithubUserRepos(param) shouldBe expected
                    }
                }
                context("without data") {
                    beforeTest {
                        coEvery {
                            repository.getUserRepositories(param.login, param.page)
                        } returns Result.Success.WithData(emptyList())
                    }
                    should("return GithubgetUserRepositoriesEmpty") {
                        val expected = GithubUserRepoEmpty
                        getGithubUserRepos(param) shouldBe expected
                    }
                }
            }

            context("getError result") {
                context("with no internet") {
                    beforeTest {
                        coEvery {
                            repository.getUserRepositories(param.login, param.page)
                        } returns Result.Error.noInternetConnection()
                    }
                    should("return correct error") {
                        val expected = GithubUserRepoError(DefaultErrorMessage.NO_INTERNET)

                        getGithubUserRepos(param) shouldBe expected
                    }
                }
                context("with netwok error") {
                    beforeTest {
                        coEvery {
                            repository.getUserRepositories(param.login, param.page)
                        } returns Result.Error(errorMessage = "Error!!", httpCode = 402)
                    }
                    should("return correct error") {
                        val expected = GithubUserRepoError("Error!!")
                        getGithubUserRepos(param) shouldBe expected
                    }
                }
            }
        }
    }

    private val mockGithubUserRepos = listOf(
        GithubUserRepo(
            name = "riza",
            description = "baik",
            stargazersCount = 10,
            updatedAt = "mockedDate"
        )
    )
}
