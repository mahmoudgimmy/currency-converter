package com.example.currencycalculator.ui.currencies.repos

import androidx.annotation.WorkerThread
import com.example.currencycalculator.data.remote.CurrencyRemote
import com.example.currencycalculator.data.local.CurrencyDao
import com.example.currencycalculator.ui.currencies.models.Currency

class CurrenciesRepository(
    private val currencyDao: CurrencyDao,
    private val currencyRemote: CurrencyRemote
) {

    suspend fun loadAllCurrenciesFromDataBase() = currencyDao.loadAllCurrencies()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCurrency(word: Currency) = currencyDao.insertNewCurrency(word)


    suspend fun loadAllCurrenciesFromNetwork() = currencyRemote.getLatestCurrencies()

}