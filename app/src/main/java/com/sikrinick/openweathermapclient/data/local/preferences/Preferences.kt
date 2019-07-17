package com.sikrinick.openweathermapclient.data.local.preferences

import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.google.gson.Gson
import com.vestiacom.mobilefailover.util.util.PreferenceDelegates

class Preferences(
    sharedPreferences: SharedPreferences,
    rxSharedPreferences: RxSharedPreferences,
    gson: Gson
): PreferenceDelegates(sharedPreferences, rxSharedPreferences, gson) {

    var userTemperature by intPref(prefKey = USER_TEMPERATURE, defaultValue = 0)
    fun observeTemperature() = observeInt(USER_TEMPERATURE)

    companion object {
        const val USER_TEMPERATURE = "temperatureType"
    }
}