package com.riza.github.home

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
sealed interface MainDisplayItemModel
interface SuccessDisplayItem

object UserDividerItemModel: MainDisplayItemModel, SuccessDisplayItem
object EmptySearchResultInfoItemModel: MainDisplayItemModel
object EndOfUsersListItemModel: MainDisplayItemModel
object LoadingItemModel: MainDisplayItemModel
object LoadingMoreItemModel: MainDisplayItemModel

data class ErrorSearchUserItemModel(val message: String): MainDisplayItemModel

data class GithubUserItemModel(
    val id: Long,
    val name: String,
    val userName: String,
    val avatarUrl: String?,
    val description: String,
    val address: String,
    val email: String
): MainDisplayItemModel, SuccessDisplayItem