package com.example.currencycalculator.ui.currencies.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.currencycalculator.databinding.ActivityMainBinding
import com.example.currencycalculator.ui.currencies.dialogs.NewCurrencyBottomDialog
import com.example.currencycalculator.ui.currencies.fragments.CurrenciesListFragment
import com.example.currencycalculator.ui.currencies.models.Currency
import com.example.currencycalculator.ui.currencies.viewmodels.CurrenciesActivityViewModel
import com.example.currencycalculator.ui.currencies.viewmodels.Mode


class CurrenciesActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val viewModel: CurrenciesActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCurrenciesListFragment()
        initializeClickListeners()

    }

    private fun initializeClickListeners() {
        binding.apply {
            swOnOff.setOnCheckedChangeListener { _, isChecked ->
                fab.isVisible = !isChecked
                val displayMode = if (isChecked) Mode.ONLINE else Mode.OFFLINE
                viewModel.changeMode(displayMode)
            }

            fab.setOnClickListener {

                val dialog = NewCurrencyBottomDialog(supportFragmentManager)

                dialog.setClickListener(object : NewCurrencyBottomDialog.BottomDialogListener {
                    override fun onButtonClicked(currency: Currency) {
                        dialog.dismiss()
                        viewModel.addNewCurrency(currency)
                    }
                })
            }
        }

    }

    private fun setupCurrenciesListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.content.id, CurrenciesListFragment.newInstance())
            .commitAllowingStateLoss()
    }
}