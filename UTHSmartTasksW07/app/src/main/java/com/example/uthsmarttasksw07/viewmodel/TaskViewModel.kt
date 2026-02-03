package com.example.uthsmarttasksw07.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasksw07.data.local.AppDatabase
import com.example.uthsmarttasksw07.data.local.TaskEntity
import com.example.uthsmarttasksw07.data.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).taskDao()
    private val repository = TaskRepository(dao)

    val tasks: StateFlow<List<TaskEntity>> =
        repository.tasks.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    // DATA MẪU
    private val sampleTasks = listOf(
        TaskEntity(
            title = "Complete Android Project",
            description = "Finish the UI, integrate API, and write documentation"
        ),
        TaskEntity(
            title = "Learn MVVM",
            description = "Understand ViewModel, Repository, Room"
        ),
        TaskEntity(
            title = "Prepare Presentation",
            description = "Slides for final demo"
        )
    )


    init {
        viewModelScope.launch {
            val currentTasks = repository.getAllOnce()
            if (currentTasks.isEmpty()) {
                sampleTasks.forEach {
                    repository.insert(it)
                }
            }
        }
    }

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            repository.insert(
                TaskEntity(title = title, description = description)
            )
        }
    }
}
