package com.sikrinick.openweathermapclient

import android.app.Application
import com.sikrinick.openweathermapclient.di.dbModule
import com.sikrinick.openweathermapclient.di.httpModule
import com.sikrinick.openweathermapclient.di.useCaseModule
import com.sikrinick.openweathermapclient.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OpenMapWeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OpenMapWeatherApplication)
            modules(
                listOf(
                    dbModule,
                    httpModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}