package com.sikrinick.openweathermapclient.domain.usecase

import com.sikrinick.openweathermapclient.data.NetworkStateRepository
import com.sikrinick.openweathermapclient.domain.Schedulers

class ObserveNetworkConnectionStateUseCase(
    private val networkStateRepository: NetworkStateRepository,
    private val schedulers: Schedulers
) {

    fun execute() = networkStateRepository.observeNetworkConnected()
        .subscribeOn(schedulers.io)
        .observeOn(schedulers.ui)

}