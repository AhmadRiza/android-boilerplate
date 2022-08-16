package com.riza.github.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

@Composable
fun AppTheme(content: @Composable () -> Unit) {

    val colorScheme = lightColors(
        primary = Color.White,
        primaryVariant = AppColor.Neutral900,
        secondary = Color.White,
        background = Color.White,
    )

    MaterialTheme(
        colors = colorScheme,
        content = content
    )
}
