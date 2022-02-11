package com.example.utmobile.ui.wallet

import java.text.NumberFormat
import java.util.*
import kotlin.random.Random


class OtterWallet(
    var otterBalance: Float,
    val otterId: String = Random.nextInt(999999).toString().format("%06d")) {
    fun addOtters(otters: Float) {
        this.otterBalance += otters
    }
    fun getBalance() : String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.setMaximumFractionDigits(0)
        format.setCurrency(Currency.getInstance("USD"))

        return format.format(this.otterBalance)
    }
}