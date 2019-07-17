package com.sikrinick.openweathermapclient.di

import android.content.Context
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.sikrinick.openweathermapclient.data.CityWeatherRepository
import com.sikrinick.openweathermapclient.data.NetworkStateRepository
import com.sikrinick.openweathermapclient.data.UserSettingsRepository
import com.sikrinick.openweathermapclient.data.local.db.AppDatabase
import com.sikrinick.openweathermapclient.data.local.preferences.Preferences
import com.sikrinick.openweathermapclient.data.platform.NetworkInfoSupplier
import org.koin.dsl.module

val dbModule = module {

    single { AppDatabase.create(get()) }
    single { get<AppDatabase>().cityDao() }
    single { CityWeatherRepository(get(), get(), get()) }

    single {
        val context = get<Context>()
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }
    single { RxSharedPreferences.create(get()) }
    single { Preferences(get(), get(), get()) }
    single { UserSettingsRepository(get()) }


    single { NetworkInfoSupplier(get()) }
    single { NetworkStateRepository(get()) }


}