package com.sikrinick.openweathermapclient.presentation.search.citylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sikrinick.openweathermapclient.R
import com.sikrinick.openweathermapclient.domain.model.City
import com.sikrinick.openweathermapclient.presentation.model.TemperatureViewState
import kotlinx.android.synthetic.main.view_city_weather.view.*

class CityListAdapter(
    private val cityClickListener: (City) -> Unit
): RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    private val cities = mutableListOf<City>()

    fun setList(newList: List<City>) {
        val callback = DiffUtil.calculateDiff(
            CityListDiffUtil(oldList = cities, newList = newList)
        )
        cities.clear()
        cities.addAll(newList)
        callback.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_city_weather,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount() = cities.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(city: City) {
            itemView.apply {
                cityName.text = city.name
                cityTemperature.text = TemperatureViewState(city.temperature).toString(itemView.context)
                //todo add weather support
                setOnClickListener { cityClickListener(city) }
            }
        }

    }
}