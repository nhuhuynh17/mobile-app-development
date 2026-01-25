package com.example.resetpasswordapplication.screen

import android.widget.Space
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.resetpasswordapplication.model.ResetPasswordViewModel

@Composable
fun EnterEmailScreen(
    viewModel: ResetPasswordViewModel,
    onNext: () -> Unit
) {
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    val isValidEmail = isValidEmail(viewModel.email.value)
    BaseAuthScreen(
        showBack = false
    ) {
        Text(
            text="Forget Password?",
            style= MaterialTheme.typography.titleMedium,
            textAlign= TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier=Modifier.height(8.dp))

        Text(
            text = "Enter your email, we will send you a verification code.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier=Modifier.height(24.dp))

        OutlinedTextField(
            value = viewModel.email.value,
            onValueChange = { viewModel.email.value = it},
            label = {Text("Your email: user@gmail.com")},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier=Modifier.height(12.dp))

        Button(
            onClick = onNext,
            enabled = isValidEmail,
            modifier=Modifier.fillMaxWidth()
        ) {
            Text("Next")
        }

        if (
            viewModel.code.value.isNotEmpty() && viewModel.newPassword.value.isNotEmpty()
        ) {
            Spacer(modifier=Modifier.height(22.dp))

            Spacer(modifier=Modifier.height(16.dp))

            Text(
                text="Last submitted information",
                style= MaterialTheme.typography.titleSmall
            )
            Spacer(modifier=Modifier.height(8.dp))

            Text("Email: ${viewModel.email.value}")
            Text("Verify Code: ${viewModel.code.value}")
            Text("Password: ${viewModel.newPassword.value}")
        }
    }
}