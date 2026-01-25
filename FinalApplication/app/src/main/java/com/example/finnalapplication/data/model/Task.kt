package com.example.finnalapplication.data.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val priority: String?= null
)
