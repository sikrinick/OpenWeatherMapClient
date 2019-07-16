package com.sikrinick.openweathermapclient.domain.model

data class City(
    val id: Int,
    val name: String,
    val countryCode: String,
    val coordinates: Coordinates
) {
    data class Coordinates(
        val lon: String,
        val lat: String
    )
}
