package com.riza.github.compose

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.riza.github.R

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
@OptIn(ExperimentalTextApi::class)
object AppFont {
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val robotoBold = FontFamily(
        Font(
            googleFont = GoogleFont("Roboto"),
            weight = FontWeight.Bold,
            fontProvider = provider
        )
    )

    val robotoRegular = FontFamily(
        Font(
            googleFont = GoogleFont("Roboto"),
            weight = FontWeight.Normal,
            fontProvider = provider
        )
    )
}