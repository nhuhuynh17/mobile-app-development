package com.example.uthfirebaseauthapp.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    fun loginSuccess(user: FirebaseUser?) {
        _isError.value = false
        _message.value = "Success!\nHi ${user?.email}"
    }

    fun loginError() {
        _isError.value = true
        _message.value = "Google Sign-in Failed\nUser canceled the Google sign-in process."
    }
}
