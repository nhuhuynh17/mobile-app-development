package com.example.productapplication.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL =
        "https://mock.apidog.com/m1/890655-872447-default/v2/"

    val api: ProductApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }
}
