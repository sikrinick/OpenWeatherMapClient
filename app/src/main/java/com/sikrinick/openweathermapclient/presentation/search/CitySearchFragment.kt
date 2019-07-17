package com.sikrinick.openweathermapclient.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sikrinick.openweathermapclient.R
import com.sikrinick.openweathermapclient.domain.model.City
import com.sikrinick.openweathermapclient.presentation.extensions.setOnQueryTextChangeListener
import com.sikrinick.openweathermapclient.presentation.search.CitySearchEvent.*
import com.sikrinick.openweathermapclient.presentation.search.CitySearchEvent.OpenScreen.CityScreen
import com.sikrinick.openweathermapclient.presentation.search.citylist.CityListAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitySearchFragment: Fragment() {

    private val viewModel by viewModel<CitySearchViewModel>()

    private val cityListAdapter = CityListAdapter{
        viewModel.cityClicked(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            R.layout.fragment_search,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView.setOnQueryTextChangeListener { viewModel.search(it) }
        cityListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cityListAdapter
        }

        viewModel.citiesObservable.observe(viewLifecycleOwner, cityListObserver)
        viewModel.eventObservable.observe(viewLifecycleOwner, eventObserver)
    }

    private val cityListObserver = Observer<List<City>> {
        cityListAdapter.setList(it)
    }

    private val eventObserver = Observer<CitySearchEvent> {
        when(it) {
            is Loading -> progressBar.isVisible = it.show

            is ConnectionState ->
                Snackbar.make(
                    view!!,
                    if (it.connected) R.string.connection_online else R.string.connection_offline,
                    Snackbar.LENGTH_SHORT
                ).show()

            is Error -> { } //TODO

            is OpenScreen -> when(it) {
                is CityScreen -> findNavController().navigate(
                    CitySearchFragmentDirections
                        .actionCitySearchFragmentToCityInfoFragment(it.cityId)
                )
            }

        }
    }

}