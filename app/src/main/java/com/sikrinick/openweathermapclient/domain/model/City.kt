package com.sikrinick.openweathermapclient.domain.model

data class City(
    val id: Int,
    val name: String,
    val countryCode: String,
    val temperature: Temperature
)
