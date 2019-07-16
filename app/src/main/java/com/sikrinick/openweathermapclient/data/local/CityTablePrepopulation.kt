package com.sikrinick.openweathermapclient.data.local

import android.content.Context
import com.sikrinick.openweathermapclient.data.model.City

class CityTablePrepopulationReader(
    val context: Context,
    val assetPath: String
) {

    fun readAsSequence(): Sequence<City> = context.assets.open(assetPath)
        .bufferedReader()
        .useLines { lines ->
            lines
                .map { it.trim() }
                .filter { it.startsWith('[') || it.startsWith(']') } //ignore json array declaration
                .chunked(9) // json object contains 9 lines
                .map { it.processChunk() }
        }

    private fun List<String>.processChunk(): City {
        val id = getValueFor(ID_IDX).toInt()
        val name = getValueFor(NAME_IDX)
        val countryCode = getValueFor(COUNTRY_IDX)
        val lon = getValueFor(LON_IDX)
        val lat = getValueFor(LAT_IDX)
        return City(
            id = id,
            name = name,
            countryCode = countryCode,
            coordinates = City.Coordinates(
                lat = lat,
                lon = lon
            )
        )
    }

    private fun List<String>.getValueFor(idx: Int) = get(idx)
        //take string after ":"
        .split(':', limit = 1)[1]
        //removing commas, whitespace, ' or " from start and end
        .trim { it.isWhitespace() ||  it == '"' || it == '\'' || it == ',' }

    companion object {
        private const val ID_IDX = 1
        private const val NAME_IDX = 2
        private const val COUNTRY_IDX = 3
        private const val LON_IDX = 5
        private const val LAT_IDX = 6
    }
}