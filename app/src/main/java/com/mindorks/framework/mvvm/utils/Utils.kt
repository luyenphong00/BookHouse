package com.mindorks.framework.mvvm.utils

import java.text.DecimalFormat

object Utils {
    fun currencyFormat(amount: String): String? {
        if (amount == ""){
            return "0"
        }
        val formatter = DecimalFormat("#,###")
        return formatter.format(amount.toDouble())
    }
}