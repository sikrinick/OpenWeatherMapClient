package com.sikrinick.openweathermapclient.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sikrinick.openweathermapclient.domain.model.City
import com.sikrinick.openweathermapclient.domain.usecase.ObserveCitiesByNameUseCase
import com.sikrinick.openweathermapclient.domain.usecase.ObserveNetworkConnectionStateUseCase
import com.sikrinick.openweathermapclient.presentation.search.CitySearchEvent.OpenScreen
import com.sikrinick.openweathermapclient.utils.replaceableDisposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class CitySearchViewModel(
    private val observeCitiesByNameUseCase: ObserveCitiesByNameUseCase,
    private val observeNetworkConnectionStateUseCase: ObserveNetworkConnectionStateUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private var searchDisposable by replaceableDisposable()

    private val citiesData = MutableLiveData<List<City>>()
    val citiesObservable: LiveData<List<City>> = citiesData

    private val eventData = MutableLiveData<CitySearchEvent>()
    val eventObservable: LiveData<CitySearchEvent> = eventData

    init {
        observeNetworkConnectionStateUseCase.execute()
            .subscribeBy(
                onNext = { eventData.value = CitySearchEvent.ConnectionState(it) },
                onError = { Timber.e(it) }
            )
            .addTo(disposables)
    }

    fun search(nameQuery: String?) {
        searchDisposable = observeCitiesByNameUseCase.execute(nameQuery)
            .doOnSubscribe {
                eventData.value = CitySearchEvent.Loading(true)
            }
            .doOnEach {
                eventData.value = CitySearchEvent.Loading(false)
            }
            .subscribeBy(
                onNext = {
                    citiesData.value = it
                },
                onError = {
                    //TODO("show error")
                    Timber.e(it)
                }
            )
            .addTo(disposables)
    }

    fun cityClicked(city: City) {
        eventData.value = OpenScreen.CityScreen(city.id)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}