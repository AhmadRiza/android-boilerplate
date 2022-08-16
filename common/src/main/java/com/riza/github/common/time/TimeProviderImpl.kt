package com.riza.github.common.time

import javax.inject.Inject

class TimeProviderImpl @Inject constructor() : TimeProvider {

    override val currentTimeInMillis: Long
        get() = System.currentTimeMillis()
}
