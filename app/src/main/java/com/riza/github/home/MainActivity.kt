package com.riza.github.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.riza.github.common.base.BaseVMComposeActivity
import com.riza.github.di.buildAppComponent

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class MainActivity: BaseVMComposeActivity<MainViewModel.Intent, MainViewModel.State,
        MainViewModel.Effect, MainViewModel>() {

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

    }

}