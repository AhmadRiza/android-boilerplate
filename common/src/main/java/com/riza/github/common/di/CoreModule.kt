package com.riza.github.common.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import com.riza.github.common.date.DateFormatter
import com.riza.github.common.date.DateFormatterImpl
import com.riza.github.common.locale.LocaleProvider
import com.riza.github.common.locale.LocaleProviderImpl
import com.riza.github.common.number.NumberFormatter
import com.riza.github.common.number.NumberFormatterImpl
import com.riza.github.common.router.NavigationRouter
import com.riza.github.common.time.TimeProvider
import com.riza.github.common.time.TimeProviderImpl
import com.riza.github.common.util.ResourceProvider
import com.riza.github.common.util.ResourceProviderImpl
import dagger.Module
import dagger.Provides
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
    fun provideTimeProvider(impl: TimeProviderImpl): TimeProvider = impl

    @Provides
    @Singleton
    fun provideLocaleProvider(impl: LocaleProviderImpl): LocaleProvider = impl

    @Provides
    @Singleton
    fun provideNumberFormatter(impl: NumberFormatterImpl): NumberFormatter = impl

    @Provides
    @Singleton
    fun provideNavigationRouter(): NavigationRouter = navigationRouter
}
