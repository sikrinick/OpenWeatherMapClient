package com.sikrinick.openweathermapclient.data

import com.sikrinick.openweathermapclient.data.local.db.CityWeatherDao
import com.sikrinick.openweathermapclient.data.local.db.model.CityWeather
import com.sikrinick.openweathermapclient.data.remote.WeatherApiService
import io.reactivex.Observable
import io.reactivex.Single


class CityWeatherRepository(
    private val weatherApiService: WeatherApiService,
    private val cityWeatherDao: CityWeatherDao,
    private val networkStateRepository: NetworkStateRepository
) {

    fun observeCitiesBySimilarName(similarName: String): Observable<List<CityWeather>> = networkStateRepository
        .observeNetworkConnected()
        .flatMapSingle { connected ->
            if (connected)
                weatherApiService.findCitiesBySimilarName(similarName)
                    .flatMapCompletable { cityWeatherDao.insert(it) }
                    .toSingleDefault(connected)
            else
                Single.just(connected)
        }
        .flatMap { cityWeatherDao.findBySimilarName(similarName) }
        .onErrorResumeNext { error: Throwable -> cityWeatherDao.findBySimilarName(similarName) }

}