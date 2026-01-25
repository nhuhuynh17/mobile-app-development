package com.example.resetpasswordapplication.screen


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
fun ConfirmScreen(
    viewModel: ResetPasswordViewModel,
    onSubmit: ()-> Unit,
    onBack: ()-> Unit
) {
    var showPassword by remember { mutableStateOf(false) }

    BaseAuthScreen(
        showBack = true,
        onBack=onBack
    ) {
        Text(
            text="Confirm",
            style= MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier= Modifier.fillMaxWidth()
        )

        Spacer(modifier= Modifier.height(8.dp))

        Text(
            text="We are here to help you!",
            style=MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value= viewModel.email.value,
            onValueChange = {},
            enabled = false,
            label= {Text(viewModel.email.value)},
            leadingIcon= {
                Icon(Icons.Default.Person,null)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier=Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.code.value,
            onValueChange = {},
            enabled = false,
            label={Text(viewModel.code.value)},
            leadingIcon = {
                Icon(Icons.Default.Lock,null)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier=Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.newPassword.value,
            onValueChange = {},
            readOnly = true,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation =
                if (showPassword) VisualTransformation.None
                else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    showPassword = !showPassword
                }) {
                    Icon(
                        imageVector =
                            if (showPassword)
                                Icons.Filled.VisibilityOff
                            else
                                Icons.Filled.Visibility,
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick= onSubmit,
            modifier=Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}