package com.riza.github.service.di

import androidx.annotation.VisibleForTesting
import com.riza.github.common.di.CoreComponentHolder
import com.riza.github.network.NetworkComponentHolder

/**
 * Created by Abghi on 12/9/20.
 * Copyright (c) 2020 Kitabisa. All rights reserved.
 **/
object GithubServiceComponentHolder {
    val donationServiceComponent: GithubServiceComponent by lazy {
        mockDonationServiceComponent?.let { return@lazy it }
        DaggerDonationServiceComponent.builder()
            .coreComponent(CoreComponentHolder.coreComponent)
            .networkComponent(NetworkComponentHolder.networkComponent)
            .build()
    }

    @get:VisibleForTesting
    @set:VisibleForTesting
    var mockDonationServiceComponent: GithubServiceComponent? = null
        set(value) {
            if (field == null) {
                field = value
            }
        }
}
