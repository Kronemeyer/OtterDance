package com.example.utmobile.ui.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WalletViewModel : ViewModel() {

    private val initialWallet = OtterWallet(0.0f)

    private val _text = MutableLiveData<String>().apply {
        value = "Account ID: ${initialWallet.otterId}"

    }
    val text: LiveData<String> = _text
}