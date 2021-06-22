package com.example.currencycalculator.ui.currencies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencycalculator.ui.currencies.models.Currency

class CurrenciesActivityViewModel:ViewModel() {
    private val _displayMode = MutableLiveData<CurrenciesActivityViewState>()
    val displayMode: LiveData<CurrenciesActivityViewState> = _displayMode
    init {
        _displayMode.value = CurrenciesActivityViewState.DisplayMode(mode = Mode.OFFLINE)
    }
    fun changeMode(mode: Mode){
        _displayMode.value = CurrenciesActivityViewState.DisplayMode(mode)
    }
    fun addNewCurrency(currency: Currency){
        _displayMode.value = CurrenciesActivityViewState.NewCurrency(currency)
    }

}