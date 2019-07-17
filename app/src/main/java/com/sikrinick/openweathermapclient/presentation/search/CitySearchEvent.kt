package com.sikrinick.openweathermapclient.presentation.search

sealed class CitySearchEvent {

    class Loading(val show: Boolean): CitySearchEvent()

    class ConnectionState(val connected: Boolean): CitySearchEvent()

    class Error(val type: Type): CitySearchEvent() {
        enum class Type {
            TOO_MANY_REQUESTS,
        }
    }

    sealed class OpenScreen: CitySearchEvent() {
        class CityScreen(val cityId: Int): OpenScreen()
    }
}