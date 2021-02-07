package com.udacity.shoestore.shoe_listing

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.shoestore.R


class ShoeListingViewModel(application: Application): AndroidViewModel(application) {



    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext


    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    fun addShoe(shoeModelName:String,shoeType: String) {
        val shoeTypeId = when (shoeType) {
            context.getString(R.string.allstar) -> R.drawable.allstar
            context.getString(R.string.boot) -> R.drawable.boot
            context.getString(R.string.flipflop) -> R.drawable.flipflop
            context.getString(R.string.high_heel) -> R.drawable.highheel
            context.getString(R.string.leather_shoe) -> R.drawable.leathershoe
            else -> R.drawable.questionmark
        }

        val newShoe = Shoe(shoeModelName, shoeTypeId)

        val list = _shoeList.value ?: mutableListOf()
        list.add(newShoe)
        _shoeList.value = list
    }


}

