package com.sikrinick.openweathermapclient.data

import com.sikrinick.openweathermapclient.data.local.db.CityWeatherDao
import com.sikrinick.openweathermapclient.data.local.db.model.CityWeather
import com.sikrinick.openweathermapclient.data.remote.WeatherApiService
import io.reactivex.Observable
import timber.log.Timber


class CityWeatherRepository(
    private val weatherApiService: WeatherApiService,
    private val cityWeatherDao: CityWeatherDao,
    private val networkStateRepository: NetworkStateRepository
) {

    fun observeCitiesBySimilarName(similarName: String): Observable<List<CityWeather>> = networkStateRepository
        .observeNetworkConnected()
        .flatMap { connected ->
            if (connected) {
                weatherApiService.findCitiesBySimilarName(similarName)
                    .doOnSuccess { cityWeatherDao.insert(it) }
                    .toObservable()
                    .onErrorResumeNext { error: Throwable ->
                        Timber.e(error, "error")
                        cityWeatherDao.findBySimilarName(similarName)
                    }
            } else {
                cityWeatherDao.findBySimilarName(similarName)
            }
        }

}