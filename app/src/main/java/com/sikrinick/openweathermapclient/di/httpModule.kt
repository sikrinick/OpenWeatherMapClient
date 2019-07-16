package com.sikrinick.openweathermapclient.di

import com.sikrinick.openweathermapclient.data.remote.OpenWeatherApi
import org.koin.dsl.module

private const val BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?"

val httpModule = module {

    // It is possible to move to separate interface, if needed, and use like this:
    //single<WeatherApi> { OpenWeatherMapApi(baseUrl = BASE_URL) }
    single { OpenWeatherApi(baseUrl = BASE_URL) }

}