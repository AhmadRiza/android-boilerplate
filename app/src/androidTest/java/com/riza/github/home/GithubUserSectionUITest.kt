package com.riza.github.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.riza.github.home.compose.GithubUserSection
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class GithubUserSectionUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun appeared_ShouldDisplayNameLabel() {
        composeTestRule.setContent {
            GithubUserSection(model = mockModel, onClickUserSection = {})
        }
        composeTestRule.onNodeWithText("Riza").assertIsDisplayed()
    }

    @Test
    fun appeared_ShouldDisplayUserNameLabel() {
        composeTestRule.setContent {
            GithubUserSection(model = mockModel, onClickUserSection = {})
        }
        composeTestRule.onNodeWithText("@AhmadRiza").assertIsDisplayed()
    }

    @Test
    fun appeared_ShouldDisplayDescriptionLabel() {
        composeTestRule.setContent {
            GithubUserSection(model = mockModel, onClickUserSection = {})
        }
        composeTestRule.onNodeWithText("description").assertIsDisplayed()
    }
    @Test
    fun appeared_ShouldDisplayAddressLabel() {
        composeTestRule.setContent {
            GithubUserSection(model = mockModel, onClickUserSection = {})
        }
        composeTestRule.onNodeWithText("Malang, Jatim").assertIsDisplayed()
    }
    @Test
    fun appeared_ShouldDisplayEmailLabel() {
        composeTestRule.setContent {
            GithubUserSection(model = mockModel, onClickUserSection = {})
        }
        composeTestRule.onNodeWithText("riza.public@gmail.com").assertIsDisplayed()
    }


    @Test
    fun clicked_InvokeTheCallback() {
        var clickedModel: GithubUserItemModel? = null
        composeTestRule.setContent {
            GithubUserSection(model = mockModel, onClickUserSection = {
                clickedModel = it
            })
        }
        composeTestRule.onNodeWithTag("gh_user_section").performClick()
        Assert.assertEquals(clickedModel, mockModel)
    }

    private val mockModel get()= GithubUserItemModel(
        id = 10,
        name = "Riza",
        userName = "@AhmadRiza",
        avatarUrl = "riza",
        description = "description",
        address = "Malang, Jatim",
        email = "riza.public@gmail.com"
    )
}