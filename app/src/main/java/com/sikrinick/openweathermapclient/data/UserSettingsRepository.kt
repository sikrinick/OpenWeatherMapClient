package com.sikrinick.openweathermapclient.data

import com.sikrinick.openweathermapclient.data.local.preferences.Preferences
import com.sikrinick.openweathermapclient.data.local.preferences.model.TemperatureType
import io.reactivex.Observable

class UserSettingsRepository (
    private val userPreferences: Preferences
) {

    var userTemperatureType: TemperatureType
        get() = TemperatureType.values()[userPreferences.userTemperature]
        set(value) { userPreferences.userTemperature = value.ordinal }

    fun observeTemperature(): Observable<TemperatureType> = userPreferences
        .observeTemperature()
        .map { TemperatureType.values()[it] }

}