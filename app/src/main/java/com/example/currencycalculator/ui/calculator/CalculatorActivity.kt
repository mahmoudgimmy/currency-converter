package com.example.currencycalculator.ui.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.currencycalculator.databinding.ActivityCalculaterBinding
import com.example.currencycalculator.ui.currencies.models.Currency

class CalculatorActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalculaterBinding
    lateinit var targetCurrency: Currency
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculaterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtras()
        binding.edBaseCurrency.doAfterTextChanged {
            if (it.toString().isEmpty())
                binding.tvTargetCurrencyRate.text = "00.00"
            else
                binding.tvTargetCurrencyRate.text =
                    String.format("%.2f", targetCurrency.rate * it.toString().toFloat())

        }
    }

    private fun getExtras() {
        intent.extras?.let {
            targetCurrency = it.getParcelable(SELECTED_CURRENCY)!!
            binding.apply {
                tvTargetCurrencyName.text = targetCurrency.name
                tvTargetCurrencyRate.text = targetCurrency.rate.toString()
            }

        }
    }

    companion object {
        const val SELECTED_CURRENCY = "selectedCurrency"
    }
}