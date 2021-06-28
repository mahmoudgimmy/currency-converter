package com.example.currencycalculator.ui.currencies.viewmodels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencycalculator.ui.calculator.CalculatorActivity
import com.example.currencycalculator.ui.currencies.models.Currency
import com.example.currencycalculator.ui.currencies.repos.CurrenciesRepository
import kotlinx.coroutines.launch


class CurrencyListViewModel(private val repository: CurrenciesRepository) : ViewModel() {
    private val _currencyList = MutableLiveData<CurrencyListViewState>()
    val currencyList: LiveData<CurrencyListViewState> = _currencyList

    fun loadAllCurrencies(mode: Mode) = viewModelScope.launch {
        _currencyList.value = CurrencyListViewState.LOADING
        val result = repository.loadCurrency(mode)
        _currencyList.value = CurrencyListViewState.SUCCESS(payload = result)

    }

    fun insertNewCurrency(currency: Currency, mode: Mode) = viewModelScope.launch {
        repository.insetCurrency(currency, mode)
        val result = repository.loadCurrency(mode)
        _currencyList.value =
            CurrencyListViewState.SUCCESS(payload = result)
    }
}