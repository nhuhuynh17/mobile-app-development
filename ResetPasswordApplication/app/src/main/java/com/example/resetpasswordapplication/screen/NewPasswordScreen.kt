package com.example.resetpasswordapplication.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.resetpasswordapplication.model.ResetPasswordViewModel

@Composable
fun NewPasswordScreen(
    viewModel: ResetPasswordViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    val isValid =
        newPassword.length >=6 &&
                newPassword==confirmPassword

    BaseAuthScreen(showBack = true) {
        Text(
            text="Create new pasword",
            style= MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier= Modifier.fillMaxWidth()
        )

        Spacer(modifier= Modifier.height(8.dp))

        Text(
            text="Your new password must be different from previously used password",
            style=MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier= Modifier.fillMaxWidth()
        )

        Spacer(modifier= Modifier.height(16.dp))

        OutlinedTextField(
            value = newPassword,
            onValueChange = {
                newPassword = it
                showError = false
            },
            label = {Text("New password")},
            singleLine = true,
            visualTransformation =
                if(showPassword)
                    VisualTransformation.None
            else
                    PasswordVisualTransformation(),

            trailingIcon = {
                IconButton(onClick= {showPassword = !showPassword}) {
                    Icon(
                        imageVector =
                            if(showPassword)
                                Icons.Default.Visibility
                        else
                        Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                showError = false
            },
            label = { Text("Confirm Password") },
            singleLine = true,
            visualTransformation =
                if (showConfirmPassword)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = showError,
            trailingIcon = {
                IconButton(onClick = { showConfirmPassword = !showConfirmPassword }) {
                    Icon(
                        imageVector =
                            if (showConfirmPassword)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        )


        if (showError) {
            Spacer(modifier=Modifier.height(8.dp))
            Text(
                text="Password do not match",
                color= MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier=Modifier.height(24.dp))

        Button(
            onClick = {
                if(isValid) {
                    viewModel.newPassword.value = newPassword
                    onNext()
                }
                else{
                    showError=true
                }
            },
            enabled = isValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset Password")
        }
    }
}