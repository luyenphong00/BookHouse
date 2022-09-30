package com.mindorks.framework.mvvm.utils

import com.mindorks.framework.mvvm.BuildConfig
import java.text.DecimalFormat

object Utils {

    const val URL_IMAGE = BuildConfig.BASE_URL_IMAGE
    fun currencyFormat(amount: String): String? {
        if (amount == ""){
            return "0"
        }
        val formatter = DecimalFormat("#,###")
        return formatter.format(amount.toDouble())
    }
}