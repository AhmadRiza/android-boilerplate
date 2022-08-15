package com.riza.github.common.di

import android.content.Context
import android.content.res.AssetManager
import com.riza.github.common.date.DateFormatter
import com.riza.github.common.locale.LocaleProvider
import com.riza.github.common.router.NavigationRouter
import com.riza.github.common.time.TimeProvider
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

    fun timeProvider(): TimeProvider

    fun localeProvider(): LocaleProvider

    fun navigationRouter(): NavigationRouter
}
