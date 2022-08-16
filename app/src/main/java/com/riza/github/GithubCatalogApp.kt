package com.riza.github

import android.app.Application
import com.riza.github.cache.di.CacheComponentHolder
import com.riza.github.common.di.CoreComponentHolder
import com.riza.github.network.NetworkComponentHolder
import com.riza.github.router.NavigationRouterImpl
import java.util.Locale

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class GithubCatalogApp: Application() {

    override fun onCreate() {
        configureCoreComponents()
        super.onCreate()
    }

    private fun configureCoreComponents() {
        CacheComponentHolder.buildComponent(this)
        CoreComponentHolder.buildComponent(
            applicationContext = this,
            sharedPreferences = CacheComponentHolder.cacheComponent.sharedPreferences(),
            navigationRouter = NavigationRouterImpl()
        )
        NetworkComponentHolder.buildComponent(
            applicationContext = this,
            githubToken = "token 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"
        )

    }
    
}