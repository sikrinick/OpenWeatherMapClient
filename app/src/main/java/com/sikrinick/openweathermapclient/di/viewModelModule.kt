package com.sikrinick.openweathermapclient.di

import com.sikrinick.openweathermapclient.presentation.cityinfo.CityInfoViewModel
import com.sikrinick.openweathermapclient.presentation.search.CitySearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { CitySearchViewModel(get(), get()) }
    viewModel { (cityId: String) -> CityInfoViewModel(cityId) }

}