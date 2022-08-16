package com.riza.github.common.router

import android.content.Context

interface NavigationRouter {
    fun goToMain(context: Context)
    fun goToDetail(context: Context, detailIntentParam: DetailIntentParam)
}
