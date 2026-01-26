package com.example.productapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productapplication.data.Product
import com.example.productapplication.data.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class ProductViewModel : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        viewModelScope.launch {
            try {
                _product.value = RetrofitInstance.api.getProducts()
            } catch (e: Exception) {
                Log.e("API_ERROR", "Lỗi gọi API", e)
            }
        }
    }
}
