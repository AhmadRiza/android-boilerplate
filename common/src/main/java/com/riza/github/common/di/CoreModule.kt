package com.riza.github.common.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import com.kitabisa.android.commonandroid.date.DateFormatter
import com.kitabisa.android.commonandroid.date.DateFormatterImpl
import com.riza.github.common.router.NavigationRouter
import com.riza.github.common.util.ResourceProvider
import com.riza.github.common.util.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
class CoreModule(
    private val sharedPreferences: SharedPreferences,
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher,
    private val navigationRouter: NavigationRouter,
) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Provides
    fun provideAssetManager(): AssetManager {
        return context.assets
    }

    @Provides
    @Singleton
    fun provideResourceProvider(): ResourceProvider {
        return ResourceProviderImpl(context)
    }

    @Provides
    @Singleton
    @IODispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = ioDispatcher

    @Provides
    @Singleton
    fun provideDateFormatter(impl: DateFormatterImpl): DateFormatter = impl

    @Provides
    @Singleton
    fun provideNavigationRouter(): NavigationRouter = navigationRouter
}
