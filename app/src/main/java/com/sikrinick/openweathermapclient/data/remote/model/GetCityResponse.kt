package com.sikrinick.openweathermapclient.data.remote.model

import com.google.gson.annotations.SerializedName

data class GetCityResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,

    @SerializedName("main")
    val mainWeatherInfo: MainWeatherInfo,

    @SerializedName("sys")
    val sysInfo: SysInfo

) {

    data class SysInfo(
        @SerializedName("country")
        val countryCode: String
    )

}