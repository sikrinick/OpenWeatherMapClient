package com.sikrinick.openweathermapclient.domain.model

data class Temperature(
    val value: Double,
    val type: Type
) {
    enum class Type {
        CELSIUS,
        FAHRENHEIT,
        KELVIN
    }

    fun to(newType: Type): Temperature {
        val newValue = when(this.type) {
            Type.CELSIUS -> when(newType) {
                Type.CELSIUS -> value
                Type.KELVIN -> value + 273.15
                Type.FAHRENHEIT -> 1.8 * value + 32
            }
            Type.KELVIN ->  when(newType) {
                Type.CELSIUS -> value - 273.15
                Type.KELVIN -> value
                Type.FAHRENHEIT -> value * 1.8 - 459.67
            }
            Type.FAHRENHEIT -> when(newType) {
                Type.CELSIUS -> (value - 32) / 1.8
                Type.KELVIN -> (value + 459.67) / 1.8
                Type.FAHRENHEIT -> value
            }
        }
        return Temperature(
            value = newValue,
            type = newType
        )
    }
}