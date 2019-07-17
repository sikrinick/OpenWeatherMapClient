package com.sikrinick.openweathermapclient.di

import com.sikrinick.openweathermapclient.domain.Schedulers
import com.sikrinick.openweathermapclient.domain.usecase.ObserveCitiesByNameUseCase
import com.sikrinick.openweathermapclient.domain.usecase.ObserveNetworkConnectionStateUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.dsl.module

val useCaseModule = module {

    single {
        Schedulers(
            ui = AndroidSchedulers.mainThread(),
            io = io.reactivex.schedulers.Schedulers.io(),
            computation = io.reactivex.schedulers.Schedulers.computation()
        )
    }

    factory { ObserveCitiesByNameUseCase(get(), get(), get()) }

    factory { ObserveNetworkConnectionStateUseCase(get(), get()) }

}