package com.sikrinick.openweathermapclient.data.remote

import com.sikrinick.openweathermapclient.data.remote.model.FindCitiesResponse
import com.sikrinick.openweathermapclient.data.remote.model.GetCityResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

// It is possible to move to separate interface, if needed
interface OpenWeatherApi {

    //api for find: https://api.openweathermap.org/data/2.5/find?q=Lon&appid=2ea38e2d2030dc1c8df2276cea21524a
    @GET("find")
    fun findCitiesBySimilarName(
        @Query("q") nameQuery: String
    ): Single<FindCitiesResponse>

    //api for get: https://api.openweathermap.org/data/2.5/weather?q=London&appid=2ea38e2d2030dc1c8df2276cea21524a
    @GET("weather")
    fun getWeatherById(
        @Query("id") id: String
    ): Single<GetCityResponse>

    companion object {
        const val APP_ID_QUERY_PARAM = "appid"
    }
}