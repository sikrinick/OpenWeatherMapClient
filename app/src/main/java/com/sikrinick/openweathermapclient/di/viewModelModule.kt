package com.sikrinick.openweathermapclient.di

import com.sikrinick.openweathermapclient.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { SearchViewModel() }

}