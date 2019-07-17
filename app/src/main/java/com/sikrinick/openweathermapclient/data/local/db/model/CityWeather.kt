package com.sikrinick.openweathermapclient.data.local.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityWeather(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "countryCode")
    val countryCode: String,

    @ColumnInfo(name = "tempKelvin")
    val tempKelvin: Double
)
