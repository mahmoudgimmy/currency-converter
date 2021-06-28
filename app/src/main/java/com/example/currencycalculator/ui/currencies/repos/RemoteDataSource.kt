package com.example.currencycalculator.ui.currencies.repos

import com.example.currencycalculator.data.remote.CurrencyRemote
import com.example.currencycalculator.ui.currencies.activities.DataSource
import com.example.currencycalculator.ui.currencies.models.Currency

class RemoteDataSource(private val currencyRemote: CurrencyRemote) : DataSource() {
    override suspend fun loadCurrency(): List<Currency> {
        return return try {
            val result = currencyRemote.getLatestCurrencies()
            result.rates.map { Currency(name = it.key, rate = it.value) }

        } catch (e: Exception) {
            return emptyList()
        }
    }

    override suspend fun insertCurrency(currency: Currency) {

    }

}
