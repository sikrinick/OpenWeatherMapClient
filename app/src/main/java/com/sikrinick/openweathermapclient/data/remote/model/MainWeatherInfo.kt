package com.sikrinick.openweathermapclient.data.remote.model

import com.google.gson.annotations.SerializedName

data class MainWeatherInfo(
    @SerializedName("temp")
    val tempKelvin: Double
)