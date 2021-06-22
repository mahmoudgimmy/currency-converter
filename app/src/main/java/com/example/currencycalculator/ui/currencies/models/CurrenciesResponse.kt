package com.example.currencycalculator.ui.currencies.models

import com.google.gson.annotations.SerializedName

data class CurrenciesResponse(@SerializedName("rates") val rates: HashMap<String, Float>)