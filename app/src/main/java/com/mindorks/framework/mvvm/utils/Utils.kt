package com.mindorks.framework.mvvm.utils

import java.text.DecimalFormat

object Utils {

    const val URL_IMAGE = "http://192.168.2.109/DoAnDuongDucThang/public/"
    fun currencyFormat(amount: String): String? {
        if (amount == ""){
            return "0"
        }
        val formatter = DecimalFormat("#,###")
        return formatter.format(amount.toDouble())
    }
}