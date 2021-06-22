package com.example.currencycalculator.ui.currencies.viewmodels

import com.example.currencycalculator.ui.currencies.models.Currency

sealed class CurrenciesActivityViewState {
    class DisplayMode(val mode: Mode): CurrenciesActivityViewState()
    class NewCurrency(val currency: Currency): CurrenciesActivityViewState()
}

sealed class Mode {
    object ONLINE : Mode()
    object OFFLINE : Mode()
}