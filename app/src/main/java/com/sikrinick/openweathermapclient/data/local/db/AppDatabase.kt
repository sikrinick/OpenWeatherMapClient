package com.sikrinick.openweathermapclient.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sikrinick.openweathermapclient.data.local.db.model.CityWeather

@Database(entities = [CityWeather::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cityDao(): CityWeatherDao

    companion object {

        fun create(context: Context) = Room
            .databaseBuilder(context, AppDatabase::class.java,"database")
            .fallbackToDestructiveMigration()
            .build()
    }

}