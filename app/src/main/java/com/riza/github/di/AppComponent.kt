package com.riza.github.di

import com.riza.github.common.di.CoreComponent
import com.riza.github.common.di.CoreComponentHolder
import com.riza.github.detail.DetailActivity
import com.riza.github.home.MainActivity
import com.riza.github.service.di.GithubServiceComponent
import com.riza.github.service.di.GithubServiceComponentHolder
import dagger.Component

@AppScope
@Component(
    dependencies = [
        CoreComponent::class,
        GithubServiceComponent::class
    ],
    modules = [AppViewModelModule::class]
)
interface AppComponent {
    fun inject(target: MainActivity)
    fun inject(target: DetailActivity)
}

fun buildAppComponent(): AppComponent {
    return DaggerAppComponent.builder()
        .coreComponent(CoreComponentHolder.coreComponent)
        .githubServiceComponent(GithubServiceComponentHolder.githubServiceComponent)
        .build()
}
