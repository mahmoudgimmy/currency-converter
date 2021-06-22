package com.example.currencycalculator.ui.currencies.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencycalculator.data.local.CurrenciesRoomDatabase
import com.example.currencycalculator.data.remote.ApiClient
import com.example.currencycalculator.databinding.FragmentCurrenciesBinding
import com.example.currencycalculator.ui.currencies.adapters.CurrenciesListClicks
import com.example.currencycalculator.ui.currencies.adapters.CurrencyAdapter
import com.example.currencycalculator.ui.currencies.models.Currency
import com.example.currencycalculator.ui.currencies.repos.CurrenciesRepository
import com.example.currencycalculator.ui.currencies.viewmodels.CurrenciesActivityViewModel
import com.example.currencycalculator.ui.currencies.viewmodels.CurrenciesActivityViewState
import com.example.currencycalculator.ui.currencies.viewmodels.CurrencyListViewModel
import com.example.currencycalculator.ui.currencies.viewmodels.CurrencyListViewState
import com.example.currencycalculator.utilities.viewModelFactory

class CurrenciesListFragment : Fragment(), CurrenciesListClicks {
    lateinit var binding: FragmentCurrenciesBinding
    lateinit var currenciesAdapter: CurrencyAdapter
    private val currenciesActivityViewModel: CurrenciesActivityViewModel by activityViewModels()
    private val currenciesFragmentViewModel: CurrencyListViewModel by viewModels {
        viewModelFactory {
            CurrencyListViewModel(
                CurrenciesRepository(
                    currencyRemote = ApiClient.CURRENCY_REMOTE,
                    currencyDao = CurrenciesRoomDatabase.getDatabase(requireContext()).currencyDao()
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrenciesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeValues()
        initializeObservable()
    }

    private fun initializeObservable() {

        currenciesActivityViewModel.displayMode.observe(viewLifecycleOwner, {
            when (it) {
                is CurrenciesActivityViewState.DisplayMode -> currenciesFragmentViewModel.loadAllCurrencies(
                    it.mode
                )
                is CurrenciesActivityViewState.NewCurrency -> currenciesFragmentViewModel.insertNewCurrency(
                    it.currency
                )
            }
        })

        currenciesFragmentViewModel.currencyList.observe(viewLifecycleOwner, {
            when (it) {
                is CurrencyListViewState.LOADING -> {
                    binding.pbLoading.isVisible = true
                }
                is CurrencyListViewState.SUCCESS -> {
                    binding.pbLoading.isVisible = false
                    currenciesAdapter.submitList(it.payload)

                }
                is CurrencyListViewState.FAILURE -> {
                    binding.pbLoading.isVisible = false
                    currenciesAdapter.submitList(it.payload)
                }
            }
        })
    }

    private fun initializeValues() {
        currenciesAdapter = CurrencyAdapter(this)
        binding.rvCurrencies.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = currenciesAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): CurrenciesListFragment {
            return CurrenciesListFragment()
        }
    }

    override fun onCurrencyClicked(currency: Currency) {
        currenciesFragmentViewModel.navigateToCalculator(requireContext(), currency)
    }
}