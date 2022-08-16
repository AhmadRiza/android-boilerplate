package com.riza.github.usecase

import com.riza.github.common.model.DefaultErrorMessage
import com.riza.github.common.model.Result
import com.riza.github.service.di.model.*
import com.riza.github.service.di.repository.GithubRepository
import com.riza.github.service.di.usecase.GetGithubUserDetail
import com.riza.github.service.di.usecase.SearchGithubUser
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

/**
 * Install Kotest android studio plugin to run these
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class SearchGithubUserTest: ShouldSpec() {
    private val repository: GithubRepository = mockk()
    private val searchGithubUser = SearchGithubUser(repository)

    init {
        val param = SearchGithubUser.Param(query = "riza", page = 1)
        context("searchGithubUser"){
            context("getSuccessful result") {
                context("with data") {
                    beforeTest {
                        coEvery {
                            repository.searchUser(param.query, param.page)
                        } returns Result.Success.WithData(mockGithubSearchUser)
                    }
                    should("return GithubUserDetail") {
                        val expected = mockGithubSearchUser
                        searchGithubUser(param) shouldBe expected
                    }
                }
                context("without data") {
                    beforeTest {
                        coEvery {
                            repository.searchUser(param.query, param.page)
                        } returns Result.Success.WithData(
                            mockGithubSearchUser.copy(items = emptyList())
                        )
                    }
                    should("return GithubSearchUserEmpty") {
                        val expected = GithubSearchUserEmpty
                        searchGithubUser(param) shouldBe expected
                    }
                }
                
            }
            
            context("getError result") {
                context("with no internet") {
                    beforeTest {
                        coEvery {
                            repository.searchUser(param.query, param.page)
                        } returns Result.Error.noInternetConnection()
                    }
                    should("return correct error") {
                        val expected = GithubSearchUserError(DefaultErrorMessage.NO_INTERNET)
                        
                        searchGithubUser(param) shouldBe expected
                    }
                }
                context("with netwok error") {
                    beforeTest {
                        coEvery {
                            repository.searchUser(param.query, param.page)
                        } returns Result.Error(errorMessage = "Error!!", httpCode = 402)
                    }
                    should("return correct error") {
                        val expected = GithubSearchUserError("Error!!")
                        searchGithubUser(param) shouldBe expected
                    }
                }

            }
        }

    }

    private val mockGithubSearchUser = GithubSearchUser(
        totalCount = 2,
        incompleteResults = false,
        items = listOf(
            GithubSearchUser.GithubUser(
                login = "riza", id = 1, avatarUrl = "riza.png"

            ),
            GithubSearchUser.GithubUser(
                login = "jokowi", id = 2, avatarUrl = "presidem.png"

            )
        )
    )
}