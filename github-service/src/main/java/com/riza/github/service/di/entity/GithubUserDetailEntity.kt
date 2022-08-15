package com.riza.github.service.di.entity

import androidx.annotation.Keep

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
@Keep
data class GithubUserDetailEntity(
    val login: String?,
    val id: Long?,
    val name: String?,
    val company: String?,
    val bio: String?,
    val location: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
)