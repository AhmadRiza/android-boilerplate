package com.riza.github.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import com.riza.github.network.exception.NoInternetConnectionException
import okhttp3.Interceptor

class InternetConnectionInterceptor(private val context: Context) {

    fun createInternetConnectionInterceptor(): Interceptor {
        return Interceptor {
            it.run {
                if (isInternetAvailable()) {
                    proceed(request())
                } else {
                    throw NoInternetConnectionException()
                }
            }
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo

        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
