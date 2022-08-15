package com.riza.github.common.locale

import java.util.Locale
import kotlinx.datetime.TimeZone

interface LocaleProvider {
    val defaultLocale: Locale
    val defaultTimeZone: TimeZone

    fun setDefaultLocale(locale: Locale)
}
