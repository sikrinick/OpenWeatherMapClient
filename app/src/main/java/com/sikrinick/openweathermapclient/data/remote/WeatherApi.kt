package com.sikrinick.openweathermapclient.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.request.host

// It is possible to move to separate interface, if needed
class OpenWeatherApi(
    baseUrl: String
) {

    private val client = HttpClient {
        defaultRequest {
            host = baseUrl
        }
    }



}