package com.sikrinick.openweathermapclient.di

import com.google.gson.Gson
import com.sikrinick.openweathermapclient.BuildConfig
import com.sikrinick.openweathermapclient.data.remote.OpenWeatherApi
import com.sikrinick.openweathermapclient.data.remote.WeatherApiService
import com.sikrinick.openweathermapclient.data.remote.interceptors.AppIdApplyingInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

// I could place it wherever, but what for?
// If user has an apk, reverse engineering is just a matter of time
private const val OPEN_WEATHER_MAP_APP_ID = "2ea38e2d2030dc1c8df2276cea21524a"


val httpModule = module {

    single {
        Gson()
    }

    single {

        OkHttpClient.Builder()
            .addInterceptor(AppIdApplyingInterceptor(OPEN_WEATHER_MAP_APP_ID))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single {
        get<Retrofit>()
            .create(OpenWeatherApi::class.java)
    }

    single {
        WeatherApiService(get())
    }

}