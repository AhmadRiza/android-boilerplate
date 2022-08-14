package com.riza.github.common.di

import android.content.Context
import android.content.res.AssetManager
import com.kitabisa.android.commonandroid.date.DateFormatter
import com.riza.github.common.router.NavigationRouter
import com.riza.github.common.util.ResourceProvider
import dagger.Component
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
@Component(
    modules = [
        CoreModule::class,
    ]
)
interface CoreComponent {

    fun context(): Context

    fun assetManager(): AssetManager

    @IODispatcher
    fun ioDispatcher(): CoroutineDispatcher

    fun resourceProvider(): ResourceProvider

    fun dateFormatter(): DateFormatter

    fun navigationRouter(): NavigationRouter
}
