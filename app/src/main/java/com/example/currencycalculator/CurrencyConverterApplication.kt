package com.example.currencycalculator

import android.app.Application
import com.example.currencycalculator.data.local.CurrenciesRoomDatabase

class CurrencyConverterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CurrenciesRoomDatabase.getDatabase(this)
    }
}