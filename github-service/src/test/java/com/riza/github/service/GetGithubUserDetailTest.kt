package com.riza.github.service

import com.riza.github.common.model.DefaultErrorMessage
import com.riza.github.common.model.Result
import com.riza.github.service.di.model.GithubUserDetail
import com.riza.github.service.di.model.GithubUserDetailError
import com.riza.github.service.di.repository.GithubRepository
import com.riza.github.service.di.usecase.GetGithubUserDetail
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

/**
 * Install Kotest android studio plugin to run these
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class GetGithubUserDetailTest : ShouldSpec() {
    private val repository: GithubRepository = mockk()
    private val getGithubUserDetail = GetGithubUserDetail(repository)

    init {
        val login = "AhmadRiza"
        context("getGithubUserDetail") {
            context("getSuccessful result") {
                beforeTest {
                    coEvery {
                        repository.getUserDetail(login)
                    } returns Result.Success.WithData(mockGithubUserDetail)
                }
                should("return GithubUserDetail") {
                    val expected = mockGithubUserDetail
                    val param = GetGithubUserDetail.Param(login)
                    getGithubUserDetail(param) shouldBe expected
                }
            }
            context("getError result") {
                context("with no internet") {
                    beforeTest {
                        coEvery {
                            repository.getUserDetail(login)
                        } returns Result.Error.noInternetConnection()
                    }
                    should("return correct error") {
                        val expected = GithubUserDetailError(DefaultErrorMessage.NO_INTERNET)
                        val param = GetGithubUserDetail.Param(login)
                        getGithubUserDetail(param) shouldBe expected
                    }
                }
                context("with netwok error") {
                    beforeTest {
                        coEvery {
                            repository.getUserDetail(login)
                        } returns Result.Error(errorMessage = "Error!!", httpCode = 402)
                    }
                    should("return correct error") {
                        val expected = GithubUserDetailError("Error!!")
                        val param = GetGithubUserDetail.Param(login)
                        getGithubUserDetail(param) shouldBe expected
                    }
                }
            }
        }
    }

    private val mockGithubUserDetail = GithubUserDetail(
        login = "AhmadRiza",
        id = 10,
        name = "Riza",
        avatarUrl = "avatar.jpg",
        company = "Kitabisa",
        bio = "Android",
        location = "Malang",
        email = "r@iza.com",
        followers = 100,
        following = 10

    )
}
