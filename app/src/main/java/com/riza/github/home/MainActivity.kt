package com.riza.github.home

import android.os.Bundle
import androidx.activity.compose.setContent
import com.riza.github.common.base.BaseVMComposeActivity
import com.riza.github.common.router.NavigationRouter
import com.riza.github.di.buildAppComponent
import javax.inject.Inject

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class MainActivity : BaseVMComposeActivity<MainViewModel.Intent, MainViewModel.State,
    MainViewModel.Effect, MainViewModel>() {

    @Inject
    lateinit var navigationRouter: NavigationRouter

    override fun inject() {
        buildAppComponent().inject(this)
    }

    override fun provideViewModel() = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel)
        }
    }

    override fun renderEffect(effect: MainViewModel.Effect) {
        when (effect) {
            is MainViewModel.Effect.GoToDetail -> {
                navigationRouter.goToDetail(this, effect.detailIntentParam)
            }
        }
    }
}
