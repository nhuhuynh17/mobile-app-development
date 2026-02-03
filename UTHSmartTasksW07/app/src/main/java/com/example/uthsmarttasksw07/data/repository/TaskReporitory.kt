package com.example.uthsmarttasksw07.data.repository

import com.example.uthsmarttasksw07.data.local.TaskDao
import com.example.uthsmarttasksw07.data.local.TaskEntity

class TaskRepository(private val dao: TaskDao) {

    val tasks = dao.getAllTasks()

    suspend fun insert(task: TaskEntity) {
        dao.insertTask(task)
    }

    suspend fun getAllOnce(): List<TaskEntity> {
        return dao.getAllOnce()
    }
}
