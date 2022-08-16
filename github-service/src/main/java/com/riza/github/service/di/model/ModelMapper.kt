package com.riza.github.service.di.model

import com.riza.github.service.di.entity.GithubSearchUserEntity
import com.riza.github.service.di.entity.GithubUserDetailEntity
import com.riza.github.service.di.entity.GithubUserRepoEntity

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

fun GithubSearchUserEntity.asModel() = GithubSearchUser(
    totalCount = totalCount ?: 0, incompleteResults = incompleteResults ?: false,
    items = items.map {
        GithubSearchUser.GithubUser(
            login = it?.login.orEmpty(),
            id = it?.id ?: 0L,
            avatarUrl = it?.avatarUrl.orEmpty()
        )
    }
)

fun GithubUserDetailEntity.asModel() = GithubUserDetail(
    login = login.orEmpty(),
    id = id ?: 0,
    name = name.orEmpty(),
    avatarUrl = avatarUrl,
    company = company.orEmpty(),
    bio = bio.orEmpty(),
    location = location.orEmpty(),
    email = email.orEmpty(),
    followers = followers ?: 0,
    following = following ?: 0

)

fun GithubUserRepoEntity.asModel() = GithubUserRepo(
    name = name.orEmpty(),
    description = description.orEmpty(),
    stargazersCount = stargazersCount ?: 0,
    updatedAt = updatedAt.orEmpty()
)
