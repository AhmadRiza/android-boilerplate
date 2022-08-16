package com.riza.github.service.di.entity

import androidx.annotation.Keep

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
@Keep
data class GithubUserRepoEntity(
    val name: String?,
    val description: String?,
    val stargazersCount: Int?,
    val updatedAt: String?
)
