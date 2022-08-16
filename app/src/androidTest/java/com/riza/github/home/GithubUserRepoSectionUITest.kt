package com.riza.github.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.riza.github.detail.DetailProfileItemModel
import com.riza.github.detail.DetailRepoItemModel
import com.riza.github.detail.compose.DetailProfileSection
import com.riza.github.detail.compose.DetailRepoSection
import com.riza.github.home.compose.GithubUserSection
import org.junit.Rule
import org.junit.Test

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class GithubUserRepoSectionUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun appeared_ShouldDisplayNameLabel() {
        composeTestRule.setContent {
            DetailRepoSection(model = mockModel)
        }
        composeTestRule.onNodeWithText("repo-riza").assertIsDisplayed()
    }

    @Test
    fun appeared_ShouldDisplayDescriptionLabel() {
        composeTestRule.setContent {
            DetailRepoSection(model = mockModel)
        }
        composeTestRule.onNodeWithText("description").assertIsDisplayed()
    }

    @Test
    fun appeared_ShouldDisplayStarLabel() {
        composeTestRule.setContent {
            DetailRepoSection(model = mockModel)
        }
        composeTestRule.onNodeWithText("10K").assertIsDisplayed()
    }

    @Test
    fun appeared_ShouldDisplayLastUpdateLabel() {
        composeTestRule.setContent {
            DetailRepoSection(model = mockModel)
        }
        composeTestRule.onNodeWithText("Yesterday").assertIsDisplayed()
    }


    private val mockModel get() = DetailRepoItemModel(
        name = "repo-riza",
        avatarUrl = null,
        description = "description",
        starsCount = "10K",
        lastUpdate = "Yesterday",
    )
}