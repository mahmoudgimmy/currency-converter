package com.example.currencycalculator.ui.currencies.repos

import com.example.currencycalculator.ui.currencies.factory.DataSourceFactory
import com.example.currencycalculator.ui.currencies.models.Currency
import com.example.currencycalculator.ui.currencies.viewmodels.Mode

class CurrenciesRepository(
    private val DataSourceFactory: DataSourceFactory
) {

    suspend fun loadCurrency(mode: Mode): List<Currency> =
        DataSourceFactory.getDataSource(mode).loadCurrency()

    suspend fun insetCurrency(currency: Currency, mode: Mode) {
        DataSourceFactory.getDataSource(mode).insertCurrency(currency)
    }

}