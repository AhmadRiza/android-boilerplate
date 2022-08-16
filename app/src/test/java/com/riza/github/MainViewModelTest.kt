package com.riza.github

import androidx.lifecycle.Observer
import com.riza.github.detail.EndOfListRepoItemModel
import com.riza.github.home.*
import com.riza.github.home.MainViewModel.*
import com.riza.github.home.usecase.SearchAndDisplayGithubUser
import com.riza.github.service.di.model.GithubSearchUser
import com.riza.github.service.di.model.GithubSearchUserEmpty
import com.riza.github.service.di.model.GithubSearchUserError
import com.riza.github.service.di.model.GithubUserDetail
import com.riza.github.service.di.usecase.GetGithubUserDetail
import com.riza.github.service.di.usecase.SearchGithubUser
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import com.riza.github.home.usecase.SearchAndDisplayGithubUser.Param as SearchParam

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest : BehaviorSpec() {
    private val searchGithubUser: SearchGithubUser = mockk()
    private val getGithubUserDetail: GetGithubUserDetail = mockk()
    private val searchAndDisplayGithubUser = SearchAndDisplayGithubUser(
        searchGithubUser = searchGithubUser,
        getGithubUserDetail = getGithubUserDetail,
        ioDispatcher = UnconfinedTestDispatcher()
    )
    private val viewModel = MainViewModel(
        searchAndDisplayGithubUser = searchAndDisplayGithubUser,
        ioDispatcher = UnconfinedTestDispatcher()
    )

    private val observedStateList = mutableListOf<State>()
    private val observedEffectList = mutableListOf<Effect>()

    private val observerState = mockk<Observer<State>>()
    private val slotState = slot<State>()

    private var effectCollectJob: Job? = null

    init {

        given("Main view model") {
            coroutineTestScope = true
            beforeEach {
                viewModel.state.observeForever(observerState)

                every {
                    observerState.onChanged(capture(slotState))
                } answers {
                    observedStateList.add(slotState.captured)
                }

                effectCollectJob = testScope.launch(UnconfinedTestDispatcher()) {
                    viewModel.effect.collect { event ->
                        event?.peekContent()?.let {
                            observedEffectList.add(it)
                        }
                    }
                }
            }

            afterEach {
                clearMocks(
                    searchGithubUser,
                    getGithubUserDetail
                )
                effectCollectJob?.cancel()
                observedEffectList.clear()
                observedStateList.clear()
            }

            When("User type query") {

                and("query is empty") {
                    then("should display empty list") {
                        viewModel.onIntentReceived(
                            Intent.OnSearchBarTextChanged("")
                        )
                        observedStateList.last().displayItems shouldBe emptyList()
                    }
                }

                and("got error") {
                    val query = "riza"
                    beforeTest {
                        coEvery {
                            searchGithubUser(SearchGithubUser.Param(query, 1))
                        } returns GithubSearchUserError("Error lo")
                    }
                    then("should display error") {
                        viewModel.onIntentReceived(Intent.OnSearchBarTextChanged(query))

                        observedStateList
                            .last()
                            .displayItems
                            .first() shouldBe ErrorSearchUserItemModel("Error lo")
                    }
                }
                and("got empty result") {
                    val query = "riza"
                    beforeTest {
                        coEvery {
                            searchGithubUser(SearchGithubUser.Param(query, 1))
                        } returns GithubSearchUserEmpty
                    }
                    then("should display empty view") {
                        viewModel.onIntentReceived(Intent.OnSearchBarTextChanged(query))

                        observedStateList
                            .last()
                            .displayItems
                            .first() shouldBe EmptySearchResultInfoItemModel
                    }
                }

                and("got result") {
                    val query = "riza"
                    beforeTest {
                        coEvery {
                            searchGithubUser(SearchGithubUser.Param(query, 1))
                        } returns mockSearchUser
                        coEvery {
                            getGithubUserDetail(GetGithubUserDetail.Param(query))
                        } returns mockGithubUserDetail
                    }
                    then("shoud show loading first") {
                        viewModel.onIntentReceived(Intent.OnSearchBarTextChanged(query))

                        observedStateList[1]
                            .displayItems
                            .first() shouldBe LoadingItemModel
                    }

                    then("should display user section") {
                        val expected = GithubUserItemModel(
                            id = mockGithubUserDetail.id,
                            name = mockGithubUserDetail.name,
                            userName = "@" + mockGithubUserDetail.login,
                            avatarUrl = mockGithubUserDetail.avatarUrl,
                            description = mockGithubUserDetail.bio +
                                    " of ${mockGithubUserDetail.company}",
                            address = mockGithubUserDetail.location,
                            email = mockGithubUserDetail.email
                        )
                        viewModel.onIntentReceived(Intent.OnSearchBarTextChanged(query))

                        observedStateList
                            .last()
                            .displayItems
                            .first() shouldBe expected
                    }


                }

            }

            When("LoadMore") {

                and("result is found") {
                    val query = "riza"
                    beforeTest {
                        coEvery {
                            searchGithubUser(SearchGithubUser.Param(query, 1))
                        } returns mockSearchUser
                        coEvery {
                            searchGithubUser(SearchGithubUser.Param(query, 2))
                        } returns mockSearchUser
                        coEvery {
                            getGithubUserDetail(GetGithubUserDetail.Param(query))
                        } returns mockGithubUserDetail
                    }
                    then("should display user sections") {
                        val model = GithubUserItemModel(
                            id = mockGithubUserDetail.id,
                            name = mockGithubUserDetail.name,
                            userName = "@" + mockGithubUserDetail.login,
                            avatarUrl = mockGithubUserDetail.avatarUrl,
                            description = mockGithubUserDetail.bio +
                                    " of ${mockGithubUserDetail.company}",
                            address = mockGithubUserDetail.location,
                            email = mockGithubUserDetail.email
                        )
                        val expected = listOf(
                            model,
                            UserDividerItemModel,
                            model
                        )
                        viewModel.onIntentReceived(Intent.OnSearchBarTextChanged(query))
                        viewModel.onIntentReceived(Intent.OnLoadMore)

                        observedStateList
                            .last()
                            .displayItems shouldBe expected
                    }
                }

                and("result is not found") {
                    val query = "riza"
                    beforeTest {
                        coEvery {
                            searchGithubUser(SearchGithubUser.Param(query, 1))
                        } returns mockSearchUser
                        coEvery {
                            searchGithubUser(SearchGithubUser.Param(query, 2))
                        } returns GithubSearchUserEmpty
                        coEvery {
                            getGithubUserDetail(GetGithubUserDetail.Param(query))
                        } returns mockGithubUserDetail
                    }
                    then("should set end of list") {
                        val model = GithubUserItemModel(
                            id = mockGithubUserDetail.id,
                            name = mockGithubUserDetail.name,
                            userName = "@" + mockGithubUserDetail.login,
                            avatarUrl = mockGithubUserDetail.avatarUrl,
                            description = mockGithubUserDetail.bio +
                                    " of ${mockGithubUserDetail.company}",
                            address = mockGithubUserDetail.location,
                            email = mockGithubUserDetail.email
                        )
                        val expected = listOf(
                            model,
                            EndOfUsersListItemModel
                        )
                        viewModel.onIntentReceived(Intent.OnSearchBarTextChanged(query))
                        viewModel.onIntentReceived(Intent.OnLoadMore)

                        observedStateList
                            .last()
                            .displayItems shouldBe expected
                    }
                }

            }

        }

    }

    private val mockSearchUser
        get() = GithubSearchUser(
            totalCount = 1,
            incompleteResults = false,
            items = listOf(
                GithubSearchUser.GithubUser(
                    login = "riza",
                    id = 123,
                    avatarUrl = "avatar.png"
                )
            )
        )


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