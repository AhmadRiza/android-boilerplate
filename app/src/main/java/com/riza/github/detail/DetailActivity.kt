package com.riza.github.detail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.riza.github.common.base.BaseVMComposeActivity
import com.riza.github.common.router.DetailIntentParam
import com.riza.github.di.buildAppComponent

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class DetailActivity: BaseVMComposeActivity<DetailViewModel.Intent, DetailViewModel.State,
        DetailViewModel.Effect, DetailViewModel>() {

    companion object {
        const val BUNDLE_DETAIL = "DetailActivity.BUNDLE_DETAIL"
    }

    private val intentParam by lazy {
        intent.extras?.getParcelable<DetailIntentParam>(BUNDLE_DETAIL)
    }

    override fun inject() {
        buildAppComponent().inject(this)
    }

    override fun provideViewModel() = DetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailScreen(viewModel)
        }
        intentParam?.let {
            dispatch(DetailViewModel.Intent.OnViewCreated(it))
        }
    }

    override fun renderEffect(effect: DetailViewModel.Effect) {
        when(effect) {
            DetailViewModel.Effect.FinishActivity -> onBackPressed()
        }
    }

}