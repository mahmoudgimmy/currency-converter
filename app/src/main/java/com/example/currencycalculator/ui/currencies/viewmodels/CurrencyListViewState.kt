package com.example.currencycalculator.ui.currencies.viewmodels

import com.example.currencycalculator.ui.currencies.models.Currency

sealed class CurrencyListViewState {
    object LOADING : CurrencyListViewState()
    class SUCCESS(val payload: List<Currency>) : CurrencyListViewState()
    class FAILURE(val payload: List<Currency> = emptyList()) : CurrencyListViewState()

}