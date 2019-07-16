package com.sikrinick.openweathermapclient.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sikrinick.openweathermapclient.data.local.city.City
import com.sikrinick.openweathermapclient.data.local.city.CityDao

@Database(entities = [City::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    public abstract fun cityDao(): CityDao

    companion object {

        fun create(context: Context) = Room
            .databaseBuilder(context, AppDatabase::class.java,"database")
            .fallbackToDestructiveMigration()
            .build()
    }

}