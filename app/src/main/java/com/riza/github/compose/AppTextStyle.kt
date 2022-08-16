package com.riza.github.compose

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
object AppTextStyle {

    val TextRegular: TextStyle = TextStyle(
        fontFamily = AppFont.Roboto,
        fontWeight = FontWeight.Normal,
        color = AppColor.Neutral900,
        fontSize = 16.sp
    )
    val TextBold: TextStyle = TextStyle(
        fontFamily = AppFont.Roboto,
        fontWeight = FontWeight.Bold,
        color = AppColor.Neutral900,
        fontSize = 16.sp
    )
}
