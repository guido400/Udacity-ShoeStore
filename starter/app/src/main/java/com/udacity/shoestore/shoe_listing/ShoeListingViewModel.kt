package com.udacity.shoestore.shoe_listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListingViewModel: ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    fun addShoe (shoe:Shoe) {
        val list = _shoeList.value ?: mutableListOf()
        list.add(shoe)
        _shoeList.value = list
    }
}