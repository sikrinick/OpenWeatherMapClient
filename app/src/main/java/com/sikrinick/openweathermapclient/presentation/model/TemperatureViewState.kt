package com.sikrinick.openweathermapclient.presentation.model

import android.content.Context
import com.sikrinick.openweathermapclient.R
import com.sikrinick.openweathermapclient.domain.model.Temperature
import com.sikrinick.openweathermapclient.utils.toStringTwoDecimal

class TemperatureViewState(
    private val temperature: Temperature
) {

    fun toString(context: Context): String {
        val symbol = when(temperature.type) {
            Temperature.Type.CELSIUS -> R.string.celsius_symbol
            Temperature.Type.KELVIN -> R.string.kelvin_symbol
            Temperature.Type.FAHRENHEIT -> R.string.fahrenheit_symbol
        }
        return "${temperature.value.toStringTwoDecimal()} ${context.getString(symbol)}"
    }

}