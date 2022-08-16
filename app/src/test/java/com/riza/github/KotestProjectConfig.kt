package com.riza.github

import com.riza.github.util.CoroutineRuleTestListener
import com.riza.github.util.InstantExecutorListener
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension

object KotestProjectConfig : AbstractProjectConfig() {

// override the properties to configure the Kotest
// see: https://kotest.io/docs/framework/project-config.html
    override fun extensions(): List<Extension> {
        return listOf(
            InstantExecutorListener(),
            CoroutineRuleTestListener(),
        )
    }
}
