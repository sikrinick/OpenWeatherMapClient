package com.sikrinick.openweathermapclient.presentation.extensions

import androidx.appcompat.widget.SearchView

fun SearchView.setOnQueryTextChangeListener(onQueryTextChanged: (String?) -> Unit) {
    setOnQueryTextListener(OnQueryTextChanged(onQueryTextChanged))
}

class OnQueryTextChanged(
    private val onQueryTextChangedCallback: (String?) -> Unit): SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String?) = true

    override fun onQueryTextChange(newText: String?): Boolean {
        onQueryTextChangedCallback(newText)
        return true
    }

}