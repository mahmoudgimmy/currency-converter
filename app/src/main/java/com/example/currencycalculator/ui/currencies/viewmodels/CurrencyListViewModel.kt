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

        when (mode) {
            is Mode.OFFLINE -> {
                try {
                    val result = repository.loadAllCurrenciesFromDataBase()
                    _currencyList.value = CurrencyListViewState.SUCCESS(payload = result)
                } catch (exception: Exception) {
                    _currencyList.value = CurrencyListViewState.FAILURE()

                }
            }
            is Mode.ONLINE -> {
                try {
                    val result = repository.loadAllCurrenciesFromNetwork()
                    val list = result.rates.map { Currency(name = it.key, rate = it.value) }
                    _currencyList.value = CurrencyListViewState.SUCCESS(payload = list)

                } catch (exception: Exception) {
                    _currencyList.value = CurrencyListViewState.FAILURE()

                }
            }
        }

    }

    fun insertNewCurrency(currency: Currency) = viewModelScope.launch {
        repository.insertCurrency(currency)
        _currencyList.value =
            CurrencyListViewState.SUCCESS(payload = repository.loadAllCurrenciesFromDataBase())
    }

    fun navigateToCalculator(context: Context, currency: Currency) {
        context.startActivity(Intent(context, CalculatorActivity::class.java).apply {
            putExtra(CalculatorActivity.SELECTED_CURRENCY, currency)
        })
    }

}