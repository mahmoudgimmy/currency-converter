package com.example.currencycalculator.data.remote

import com.example.currencycalculator.ui.currencies.models.CurrenciesResponse
import com.example.currencycalculator.data.remote.ApiClient.FIXER_API_KEY
import retrofit2.http.GET

interface CurrencyRemote {
    @GET("latest?access_key=$FIXER_API_KEY")
    suspend fun getLatestCurrencies(): CurrenciesResponse
}