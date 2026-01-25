package com.example.paymentoopapplication

import android.icu.text.CaseMap
import androidx.annotation.DrawableRes

sealed class PaymentMethod(
    val title: String,
    @DrawableRes val icon : Int
) {
    object PayPal : PaymentMethod("PayPal",R.drawable.logo_paypal)
    object GooglePay : PaymentMethod("GooglePay",R.drawable.logo_ggpay)
    object ApplePay: PaymentMethod("ApplePay", R.drawable.logo_applepay)
}