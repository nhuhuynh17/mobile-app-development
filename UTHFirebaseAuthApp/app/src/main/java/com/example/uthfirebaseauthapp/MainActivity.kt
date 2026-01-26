package com.example.uthfirebaseauthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uthfirebaseauthapp.screen.AuthScreen
import com.example.uthfirebaseauthapp.ui.theme.UTHFirebaseAuthAppTheme
import com.example.uthfirebaseauthapp.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: AuthViewModel = viewModel()
            val message by viewModel.message.collectAsState()
            val isError by viewModel.isError.collectAsState()

            AuthScreen(
                onLoginClick = {
                    //giả lập
                    val success = listOf(true, false).random()
                    if (success) {
                        viewModel.loginSuccess(FirebaseAuth.getInstance().currentUser)
                    } else {
                        viewModel.loginError()
                    }
                },
                message = message,
                isError = isError
            )
        }
    }
}
