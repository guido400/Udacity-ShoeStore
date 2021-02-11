package com.udacity.shoestore.shoe_listing

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe


class ShoeListingViewModel(application: Application): AndroidViewModel(application){

    val shoeNameText = MutableLiveData<String>("")
    val shoeSizeText = MutableLiveData<String>("")
    val shoeCompanyText = MutableLiveData<String>("")
    val shoeDescriptionText = MutableLiveData<String>("")

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    fun addShoe() {
        val shoeSizeInt = shoeSizeText.value?.toInt() ?: 0

        val newShoe = Shoe(shoeNameText.value.toString(),
            shoeSizeInt,
            shoeCompanyText.value.toString(),
            shoeDescriptionText.value.toString())

        val list = _shoeList.value ?: mutableListOf()
        list.add(newShoe)
        _shoeList.value = list
    }
}

