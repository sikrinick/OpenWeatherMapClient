package com.sikrinick.openweathermapclient.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sikrinick.openweathermapclient.data.local.db.model.CityWeather
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface CityWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cities: List<CityWeather>): Completable

    @Query("SELECT * FROM CityWeather WHERE name LIKE '%' || :name || '%'")
    fun findBySimilarName(name: String): Observable<List<CityWeather>>
}