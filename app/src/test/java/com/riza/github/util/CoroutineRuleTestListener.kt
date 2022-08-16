package com.riza.github.util

import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.Spec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

/**
 * Created by Abghi on 03/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 **/
@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineRuleTestListener : TestListener {

    override suspend fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        Dispatchers.resetMain()
    }

    override suspend fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }
}
