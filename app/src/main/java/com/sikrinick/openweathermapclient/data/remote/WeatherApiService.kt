package com.sikrinick.openweathermapclient.data.remote

import com.sikrinick.openweathermapclient.data.local.db.model.CityWeather
import com.sikrinick.openweathermapclient.data.remote.model.GetCityResponse
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable

class WeatherApiService(
    private val weatherApi: OpenWeatherApi
) {

    fun findCitiesBySimilarName(similarName: String): Single<List<CityWeather>> = weatherApi
        .findCitiesBySimilarName(similarName)
        .flatMapObservable { it.citiesResponses.toObservable() }
        .map { it.toCityWeather() }
        .toList()

    fun getWeatherById(id: String): Single<CityWeather> = weatherApi
        .getWeatherById(id)
        .map { it.toCityWeather() }


    private fun GetCityResponse.toCityWeather() = CityWeather(
        id = id,
        name = name,
        countryCode = sysInfo.countryCode,
        tempKelvin = mainWeatherInfo.tempKelvin
    )
}