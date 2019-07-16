package com.sikrinick.openweathermapclient.data.local.city

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {

    @Insert
    suspend fun insert(city: City)

    @Query("SELECT * FROM CITY WHERE name LIKE '%' || :name || '%'")
    suspend fun findByName(name: String): List<City>
}