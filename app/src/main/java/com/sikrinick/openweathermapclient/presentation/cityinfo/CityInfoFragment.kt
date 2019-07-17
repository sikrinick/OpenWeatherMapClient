package com.sikrinick.openweathermapclient.presentation.cityinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sikrinick.openweathermapclient.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class CityInfoFragment: Fragment() {

    private val args: CityInfoFragmentArgs by navArgs()

    private val viewModel by viewModel<CityInfoViewModel> {
        parametersOf(args.cityId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            R.layout.fragment_city_info,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo setup view
    }

}