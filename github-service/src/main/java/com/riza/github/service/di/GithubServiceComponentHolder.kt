package com.riza.github.service.di

import androidx.annotation.VisibleForTesting
import com.riza.github.common.di.CoreComponentHolder
import com.riza.github.network.NetworkComponentHolder

object GithubServiceComponentHolder {
    val githubServiceComponent: GithubServiceComponent by lazy {
        mockGithubServiceComponent?.let { return@lazy it }
        DaggerGithubServiceComponent.builder()
            .coreComponent(CoreComponentHolder.coreComponent)
            .networkComponent(NetworkComponentHolder.networkComponent)
            .build()
    }

    @get:VisibleForTesting
    @set:VisibleForTesting
    var mockGithubServiceComponent: GithubServiceComponent? = null
        set(value) {
            if (field == null) {
                field = value
            }
        }
}
