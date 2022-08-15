package com.riza.github.service.di

import com.riza.github.service.di.repository.GithubRepository
import com.riza.github.service.di.repository.GithubRepositoryImpl
import com.riza.github.service.di.source.remote.GithubService
import dagger.Module
import dagger.Provides
import javax.inject.Named
import retrofit2.Retrofit


@Module
class GithubServiceModule {

    @GithubServiceScope
    @Provides
    fun provideGithubRepository(
        githubRepositoryImpl: GithubRepositoryImpl
    ): GithubRepository = githubRepositoryImpl

    @GithubServiceScope
    @Provides
    fun provideGithubService(
        @Named("github-retrofit") retrofit: Retrofit,
    ): GithubService {
        return retrofit.create(GithubService::class.java)
    }
}
