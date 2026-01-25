package com.example.finnalapplication.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finnalapplication.data.model.Task
import com.example.finnalapplication.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    var task by mutableStateOf<Task?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun loadDetail(id: Int) {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = RetrofitClient.api.getTaskDetail(id)
                task = response.data

            } catch (e: Exception) {
                task = null
            } finally {
                isLoading = false
            }
        }
    }

    fun deleteTask(id: Int, onDone: () -> Unit) {
        viewModelScope.launch {
            try {
                RetrofitClient.api.deleteTask(id)
                onDone()
            } catch (e: Exception) {
                // ignore
            }
        }
    }
}
