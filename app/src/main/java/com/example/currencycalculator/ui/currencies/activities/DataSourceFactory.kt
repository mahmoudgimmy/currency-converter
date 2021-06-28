package com.example.currencycalculator.ui.currencies.activities

import android.content.Context
import com.example.currencycalculator.data.local.CurrenciesRoomDatabase
import com.example.currencycalculator.data.remote.ApiClient
import com.example.currencycalculator.ui.currencies.repos.LocalDataSource
import com.example.currencycalculator.ui.currencies.repos.RemoteDataSource
import com.example.currencycalculator.ui.currencies.viewmodels.Mode

class DataSourceFactory(private val context: Context) {
    fun getDataSource(mode: Mode): DataSource {
        return when (mode) {
            is Mode.ONLINE -> {
                RemoteDataSource(ApiClient.CURRENCY_REMOTE)
            }
            is Mode.OFFLINE -> {
                LocalDataSource(CurrenciesRoomDatabase.getDatabase(context).currencyDao())
            }
        }

    }
}