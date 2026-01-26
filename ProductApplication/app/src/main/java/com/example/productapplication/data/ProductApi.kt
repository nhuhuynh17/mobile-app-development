package com.example.productapplication.data

import retrofit2.http.GET

interface ProductApi {

    @GET("product")
    suspend fun getProducts(): Product
}
