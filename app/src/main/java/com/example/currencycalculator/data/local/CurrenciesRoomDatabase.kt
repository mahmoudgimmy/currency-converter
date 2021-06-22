package com.example.currencycalculator.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currencycalculator.ui.currencies.models.Currency

@Database(entities = [Currency::class], version = 1, exportSchema = false)
abstract class CurrenciesRoomDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var INSTANCE: CurrenciesRoomDatabase? = null

        fun getDatabase(context: Context): CurrenciesRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrenciesRoomDatabase::class.java,
                    "currency_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}