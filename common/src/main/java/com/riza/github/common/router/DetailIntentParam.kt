package com.riza.github.common.router

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
@Parcelize
data class DetailIntentParam(
    val login: String,
    val id: Long,
    val name: String,
    val avatarUrl: String?,
    val company: String,
    val bio: String,
    val location: String,
    val email: String,
    val followers: Int,
    val following: Int,
) : Parcelable
