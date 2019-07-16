package com.sikrinick.openweathermapclient.data.remote.interceptors

import com.sikrinick.openweathermapclient.data.remote.OpenWeatherApi
import okhttp3.Interceptor
import okhttp3.Response

class AppIdApplyingInterceptor(private val appId: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        //Adding appid=111111
        val url = request
            .url()
            .newBuilder()
            .addQueryParameter(OpenWeatherApi.APP_ID_QUERY_PARAM, appId)
            .build()

        return chain.proceed(
            request
                .newBuilder()
                .url(url)
                .build()
        )

    }

}