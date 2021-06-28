package com.example.currencycalculator.ui.currencies.factory

import com.example.currencycalculator.ui.currencies.models.Currency

abstract class DataSource {
    abstract suspend fun loadCurrency(): List<Currency>
    abstract suspend fun insertCurrency(currency: Currency)
}