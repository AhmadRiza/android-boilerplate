package com.riza.github.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riza.github.common.di.ViewModelKey
import com.riza.github.home.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AppViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    fun bindAppViewModelFactory(
        viewModelFactory: AppViewModelFactory
    ): ViewModelProvider.Factory
}
