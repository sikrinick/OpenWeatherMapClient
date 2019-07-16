package com.sikrinick.openweathermapclient.di

import com.sikrinick.openweathermapclient.data.local.AppDatabase
import org.koin.dsl.module

val dbModule = module {

    single { AppDatabase.create(get()) }

}