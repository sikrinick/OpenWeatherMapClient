package com.sikrinick.openweathermapclient.di

import com.sikrinick.openweathermapclient.data.remote.OpenWeatherApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://api.openweathermap.org/data/2.5"
private const val OPEN_WEATHER_MAP_APP_ID = "2ea38e2d2030dc1c8df2276cea21524a"


val httpModule = module {

    single {
        OkHttpClient.Builder()
            //.addInterceptor()
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>()
            .create(OpenWeatherApi::class.java)
    }

}