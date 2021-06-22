package com.example.currencycalculator.ui.currencies.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.example.currencycalculator.databinding.DialogBottomSheetBinding
import com.example.currencycalculator.ui.currencies.models.Currency
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewCurrencyBottomDialog(supportFragmentManager: FragmentManager) :
    BottomSheetDialogFragment() {

    private lateinit var listner: BottomDialogListener
    private lateinit var binding: DialogBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogBottomSheetBinding.inflate(inflater)
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btOk.setOnClickListener {
            listner.onButtonClicked(
                Currency(
                    name = binding.edCurrencyName.text.toString(),
                    rate = binding.edCurrencyRate.text.toString().toFloat()
                )
            )
        }

    }

    init {
        this.show(supportFragmentManager, null)
    }


    fun setClickListener(listener: BottomDialogListener) {
        listner = listener
    }

    interface BottomDialogListener {
        fun onButtonClicked(currency: Currency)
    }
}