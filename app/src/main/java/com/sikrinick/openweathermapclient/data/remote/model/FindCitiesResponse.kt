package com.sikrinick.openweathermapclient.data.remote.model

import com.google.gson.annotations.SerializedName

data class FindCitiesResponse(
    @SerializedName("list")
    val citiesResponses: List<GetCityResponse>
)