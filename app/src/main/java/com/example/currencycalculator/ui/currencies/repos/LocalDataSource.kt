package com.example.currencycalculator.ui.currencies.repos

import com.example.currencycalculator.data.local.CurrencyDao
import com.example.currencycalculator.ui.currencies.activities.DataSource
import com.example.currencycalculator.ui.currencies.models.Currency

class LocalDataSource(private val currencyDao: CurrencyDao) : DataSource() {
    override suspend fun loadCurrency(): List<Currency> {
        return try {
            currencyDao.loadAllCurrencies()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun insertCurrency(currency: Currency) {
        currencyDao.insertNewCurrency(currency)
    }
}