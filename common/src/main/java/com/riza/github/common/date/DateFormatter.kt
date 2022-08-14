package com.kitabisa.android.commonandroid.date

import java.util.Date

/**
 * Created by ahmadriza on 06/12/21
 * Copyright (c) 2021 Kitabisa all rights reserved
 */

interface DateFormatter {
    fun getDateOrNull(date: String, dateFormat: DateFormat): Date?
    fun getFormattedDate(date: Date, dateFormat: DateFormat): String
    fun getFormattedDate(date: Long, dateFormat: DateFormat): String
    fun getFormattedDateOrEmpty(
        date: String,
        originFormat: DateFormat,
        targetFormat: DateFormat
    ): String
    fun getRelativelyFormattedDate(date: Long, dateFormat: DateFormat): String
    fun checkIsValidDate(dateString: String, dateFormat: DateFormat): Boolean
}
