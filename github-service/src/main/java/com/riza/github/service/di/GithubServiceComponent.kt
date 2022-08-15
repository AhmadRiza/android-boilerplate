package com.riza.github.service.di


import com.riza.github.common.di.CoreComponent
import com.riza.github.network.NetworkComponent
import com.riza.github.service.di.usecase.GetGithubUserDetail
import com.riza.github.service.di.usecase.GetGithubUserRepos
import com.riza.github.service.di.usecase.SearchGithubUser
import dagger.Component

/**
 * Created by Abghi on 12/9/20.
 * Copyright (c) 2020 Kitabisa. All rights reserved.
 **/
@GithubServiceScope
@Component(
    modules = [
        GithubServiceModule::class
    ],
    dependencies = [
        CoreComponent::class,
        NetworkComponent::class
    ]
)
interface GithubServiceComponent {
    fun getGithubUserDetail(): GetGithubUserDetail
    fun getGithubUserRepos(): GetGithubUserRepos
    fun searchGithubUser(): SearchGithubUser
}
