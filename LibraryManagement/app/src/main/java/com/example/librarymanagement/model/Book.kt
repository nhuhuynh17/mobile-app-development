package com.example.librarymanagement.model

data class Book(
    val id: Int,
    val name: String,
    var isBorrowed: Boolean = false
)
