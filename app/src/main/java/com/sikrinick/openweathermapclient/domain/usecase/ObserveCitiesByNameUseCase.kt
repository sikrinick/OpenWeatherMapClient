package com.sikrinick.openweathermapclient.domain.usecase

import com.sikrinick.openweathermapclient.data.CityWeatherRepository
import com.sikrinick.openweathermapclient.data.UserSettingsRepository
import com.sikrinick.openweathermapclient.data.local.db.model.CityWeather
import com.sikrinick.openweathermapclient.data.local.preferences.model.TemperatureType
import com.sikrinick.openweathermapclient.domain.Schedulers
import com.sikrinick.openweathermapclient.domain.model.City
import com.sikrinick.openweathermapclient.domain.model.Temperature
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables

class ObserveCitiesByNameUseCase(
    private val cityWeatherRepository: CityWeatherRepository,
    private val userSettingsRepository: UserSettingsRepository,
    private val schedulers: Schedulers
) {

    fun execute(nameQuery: String?): Observable<List<City>> {
        val cityWeatherSingle = if (nameQuery.isNullOrBlank()) {
            Observable.just(emptyList())
        } else {
            cityWeatherRepository.observeCitiesBySimilarName(nameQuery)
        }
        return Observables
            .combineLatest(
                cityWeatherSingle,
                userSettingsRepository.observeTemperature()
            )
            .map { (list, temperatureType) ->
                list.map { it.mapToCity(temperatureType) }
            }
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.ui)
    }

    private fun CityWeather.mapToCity(type: TemperatureType): City {
        val useCaseType = when(type) {
            TemperatureType.CELSIUS -> Temperature.Type.CELSIUS
            TemperatureType.KELVIN -> Temperature.Type.KELVIN
            TemperatureType.FAHRENHEIT -> Temperature.Type.FAHRENHEIT
        }

        return City(
            id = id,
            name = name,
            countryCode = countryCode,
            temperature = Temperature(tempKelvin, Temperature.Type.KELVIN).to(useCaseType)
        )
    }

}