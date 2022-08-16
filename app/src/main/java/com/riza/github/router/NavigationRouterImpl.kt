package com.riza.github.router

import android.content.Context
import android.content.Intent
import com.riza.github.common.router.DetailIntentParam
import com.riza.github.common.router.NavigationRouter
import com.riza.github.detail.DetailActivity
import com.riza.github.home.MainActivity

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
class NavigationRouterImpl : NavigationRouter {
    override fun goToMain(context: Context) {
        context.run {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    override fun goToDetail(context: Context, detailIntentParam: DetailIntentParam) {
        context.run {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.BUNDLE_DETAIL, detailIntentParam)
            }
            startActivity(intent)
        }
    }
}
