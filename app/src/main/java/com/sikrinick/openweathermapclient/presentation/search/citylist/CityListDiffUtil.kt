package com.sikrinick.openweathermapclient.presentation.search.citylist

import androidx.recyclerview.widget.DiffUtil
import com.sikrinick.openweathermapclient.domain.model.City

class CityListDiffUtil(
    private val oldList: List<City>,
    private val newList: List<City>
): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPos: Int, newItemPos: Int) = oldList[oldItemPos].id == newList[newItemPos].id

    override fun areContentsTheSame(oldItemPos: Int, newItemPos: Int) = oldList[oldItemPos] == newList[newItemPos]

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

}