package com.example.finnalapplication.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finnalapplication.data.model.Task
import com.example.finnalapplication.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var tasks by mutableStateOf<List<Task>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    fun loadTasks() {
        viewModelScope.launch {
            isLoading = true
            try {
                tasks = RetrofitClient.api.getTasks().data
            } catch (e: Exception) {
                tasks = emptyList()
            } finally {
                isLoading = false
            }
        }
    }

    fun removeTask(id: Int) {
        tasks = tasks.filterNot { it.id == id }
    }
}
