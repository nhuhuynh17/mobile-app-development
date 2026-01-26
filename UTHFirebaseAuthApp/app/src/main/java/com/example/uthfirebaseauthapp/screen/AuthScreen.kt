package com.example.uthfirebaseauthapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen(
    onLoginClick: () -> Unit,
    message: String? = null,
    isError: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = onLoginClick,
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("Login by Gmail")
        }

        Spacer(modifier = Modifier.height(16.dp))

        message?.let {
            Text(
                text = it,
                color = if (isError) Color.Red else Color.Green
            )
        }
    }
}
