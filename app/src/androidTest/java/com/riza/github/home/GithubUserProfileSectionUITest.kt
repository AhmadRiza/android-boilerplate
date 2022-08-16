package com.riza.github.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.riza.github.detail.DetailProfileItemModel
import com.riza.github.detail.compose.DetailProfileSection
import com.riza.github.home.compose.GithubUserSection
import org.junit.Rule
import org.junit.Test

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class GithubUserProfileSectionUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun appeared_ShouldDisplayNameLabel() {
        composeTestRule.setContent {
            DetailProfileSection(model = mockModel)
        }
        composeTestRule.onNodeWithText("Riza").assertIsDisplayed()
    }

    @Test
    fun appeared_ShouldDisplayUserNameLabel() {
        composeTestRule.setContent {
            DetailProfileSection(model = mockModel)
        }
        composeTestRule.onNodeWithText("@AhmadRiza").assertIsDisplayed()
    }

    @Test
    fun appeared_ShouldDisplayDescriptionLabel() {
        composeTestRule.setContent {
            DetailProfileSection(model = mockModel)
        }
        composeTestRule.onNodeWithText("description").assertIsDisplayed()
    }
    @Test
    fun appeared_ShouldDisplayAddressLabel() {
        composeTestRule.setContent {
            DetailProfileSection(model = mockModel)
        }
        composeTestRule.onNodeWithText("Malang, Jatim").assertIsDisplayed()
    }
    @Test
    fun appeared_ShouldDisplayEmailLabel() {
        composeTestRule.setContent {
            DetailProfileSection(model = mockModel)
        }
        composeTestRule.onNodeWithText("riza.public@gmail.com").assertIsDisplayed()
    }
    @Test
    fun appeared_ShouldDisplayFollowersLabel() {
        composeTestRule.setContent {
            DetailProfileSection(model = mockModel)
        }
        composeTestRule.onNodeWithText("10K").assertIsDisplayed()
        composeTestRule.onNodeWithText("1K").assertIsDisplayed()
    }

    private val mockModel get() = DetailProfileItemModel(
        name = "Riza",
        userName = "@AhmadRiza",
        avatarUrl = "riza",
        description = "description",
        address = "Malang, Jatim",
        email = "riza.public@gmail.com",
        followers = "10K",
        following = "1K",
    )
}