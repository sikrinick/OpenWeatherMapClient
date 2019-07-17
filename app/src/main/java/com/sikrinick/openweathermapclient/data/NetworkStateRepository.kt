package com.sikrinick.openweathermapclient.data

import com.sikrinick.openweathermapclient.data.platform.NetworkInfoSupplier

class NetworkStateRepository(
    private val networkInfoSupplier: NetworkInfoSupplier
) {

    fun observeNetworkConnected() = networkInfoSupplier.observeNetworkConnected()

}