package com.riza.github.service


import com.riza.github.common.entities.NetworkResult
import com.riza.github.common.model.Result
import com.riza.github.service.di.entity.GithubSearchUserEntity
import com.riza.github.service.di.entity.GithubUserDetailEntity
import com.riza.github.service.di.entity.GithubUserRepoEntity
import com.riza.github.service.di.model.GithubSearchUser
import com.riza.github.service.di.model.GithubUserDetail
import com.riza.github.service.di.model.GithubUserRepo
import com.riza.github.service.di.repository.GithubRepositoryImpl
import com.riza.github.service.di.source.remote.GithubService
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import retrofit2.Response

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class GithubRepositoryImplTest : ShouldSpec() {
    private val githubService: GithubService = mockk()
    private val githubRepository = GithubRepositoryImpl(githubService)

    init {

        context("search user") {
            context("get success network result") {
                beforeTest {
                    coEvery {
                        githubService.searchUser("riza", 1)
                    } returns Response.success(mockSearchUserEntity)
                }
                should("return success result with data") {
                    val expected = Result.Success.WithData(
                        GithubSearchUser(
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
                    )
                    githubRepository.searchUser("riza", 1) shouldBe expected
                }
            }
        }

        context("get user detail") {
            context("get success network result") {
                beforeTest {
                    coEvery {
                        githubService.getUserDetail("riza", )
                    } returns Response.success(mockGithubUserDetailEntity)
                }
                should("return success result with data") {
                    val expected = Result.Success.WithData(
                        GithubUserDetail(
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
                    )
                    githubRepository.getUserDetail("riza") shouldBe expected
                }
            }
        }

        context("get user repos") {
            context("get success network result") {
                beforeTest {
                    coEvery {
                        githubService.getUserRepos("riza", 1)
                    } returns Response.success(mockGithubRepos)
                }
                should("return success result with data") {
                    val expected = Result.Success.WithData(
                        listOf(
                            GithubUserRepo(
                            name = "riza",
                            description = "baik",
                            stargazersCount = 10,
                            updatedAt = "mockedDate"
                        ))
                    )
                    githubRepository.getUserRepositories(
                        "riza",
                        1
                    ) shouldBe expected
                }
            }
        }

    }

    private val mockSearchUserEntity
        get() = GithubSearchUserEntity(
            totalCount = 2,
            incompleteResults = false,
            items = listOf(
                GithubSearchUserEntity.GithubUserEntity(
                    login = "riza", id = 1, avatarUrl = "riza.png"

                ),
                GithubSearchUserEntity.GithubUserEntity(
                    login = "jokowi", id = 2, avatarUrl = "presidem.png"

                )
            )
        )

    private val mockGithubUserDetailEntity
        get() = GithubUserDetailEntity(
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

    private val mockGithubRepos get() = listOf(
        GithubUserRepoEntity(
            name = "riza",
            description = "baik",
            stargazersCount = 10,
            updatedAt = "mockedDate"
        )
    )
}