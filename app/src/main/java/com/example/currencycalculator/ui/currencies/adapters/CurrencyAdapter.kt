package com.example.currencycalculator.ui.currencies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencycalculator.databinding.ItemCurrencyBinding
import com.example.currencycalculator.ui.currencies.models.Currency

class CurrencyAdapter(private val currenciesListClicks: CurrenciesListClicks) :
    ListAdapter<Currency, CurrencyAdapter.CurrencyViewHolder>(DiffCheck.Currency_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {


        return CurrencyViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bindTo(getItem(position), currenciesListClicks)
    }

    class CurrencyViewHolder(private val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindTo(currency: Currency, currenciesListClicks: CurrenciesListClicks) {
            binding.apply {
                tvCurrencyName.text = currency.name
                llMovie.setOnClickListener {
                    currenciesListClicks.onCurrencyClicked(currency)
                }
                tvCurrencyRate.text = currency.rate.toString()
            }

        }
    }

}

object DiffCheck {
    var Currency_DIFF_CALLBACK: DiffUtil.ItemCallback<Currency> =
        object :
            DiffUtil.ItemCallback<Currency>() {

            override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
                return oldItem.name == newItem.name
            }
        }
}

interface CurrenciesListClicks {
    fun onCurrencyClicked(currency: Currency)
}
