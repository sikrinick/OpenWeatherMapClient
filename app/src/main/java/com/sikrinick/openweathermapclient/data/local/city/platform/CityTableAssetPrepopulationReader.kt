package com.sikrinick.openweathermapclient.data.local.city.platform

import android.content.Context
import com.sikrinick.openweathermapclient.data.local.city.CityTablePrepopulationReader

class CityTableAssetPrepopulationReader(
    private val context: Context
): CityTablePrepopulationReader({ context.assets.open("city.list.json") })