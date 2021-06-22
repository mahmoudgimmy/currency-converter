package com.example.currencycalculator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencycalculator.ui.currencies.models.Currency

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency")
    suspend fun loadAllCurrencies(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewCurrency(currency: Currency)


}