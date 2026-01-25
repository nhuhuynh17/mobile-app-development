package com.example.resetpasswordapplication.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ResetPasswordViewModel : ViewModel() {
    val email = mutableStateOf("")
    val code = mutableStateOf("")
    val newPassword = mutableStateOf("")

    fun resetPassword () {
        email.value=""
        code.value=""
        newPassword.value=""
    }
}