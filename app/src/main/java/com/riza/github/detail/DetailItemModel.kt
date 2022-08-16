package com.riza.github.detail

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

sealed interface DetailDisplayItemModel
interface DetailDisplaySuccessItemModel

data class DetailProfileItemModel(
    val avatarUrl: String,
    val name: String,
    val userName: String,
    val description: String,
    val followers: String,
    val following: String,
    val address: String,
    val email: String
): DetailDisplayItemModel, DetailDisplaySuccessItemModel

data class DetailRepoItemModel(
    val avatarUrl: String,
    val name: String,
    val description: String,
    val starsCount: String,
    val lastUpdate: String
): DetailDisplayItemModel, DetailDisplaySuccessItemModel

object DetailDisplayDividerItemModel: DetailDisplayItemModel, DetailDisplaySuccessItemModel
object LoadingRepoItemModel: DetailDisplayItemModel
object EndOfListRepoItemModel: DetailDisplayItemModel
data class DetailRepoErrorItemModel(val message: String): DetailDisplayItemModel
