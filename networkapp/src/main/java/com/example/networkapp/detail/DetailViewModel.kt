package com.example.networkapp.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.networkapp.R
import com.example.networkapp.network.MarsProperty

class DetailViewModel(marsProperty: MarsProperty, app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<MarsProperty>()
    val selectedProperty: LiveData<MarsProperty>
        get() = _selectedProperty

    val displayPropertyPrice = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            when(it.isRental) {
                true -> R.string.display_price_monthly_rental
                false -> R.string.display_price
            }, it.price
        )
    }

    val displayPropertyType = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            when(it.isRental) {
                true -> R.string.type_rent
                false -> R.string.type_sale
            }
        )
    }

    init {
        _selectedProperty.value = marsProperty
    }
}