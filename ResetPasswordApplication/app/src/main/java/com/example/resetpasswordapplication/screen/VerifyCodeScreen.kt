package com.example.resetpasswordapplication.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VerifyCodeScreen(
    onBack: () -> Unit,
    onNext: (String) -> Unit
) {
    val generatedOtp = remember {
        (100000..999999).random().toString()
    }

    val otpValues = remember { mutableStateListOf("", "", "", "", "", "") }
    var showError by remember { mutableStateOf(false) }

    val focusRequesters = List(6) { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val isOtpComplete = otpValues.all { it.isNotEmpty() }

    // reset otp
    fun resetOtp() {
        for (i in 0..5) {
            otpValues[i] = ""
        }
        showError = false
        focusRequesters[0].requestFocus()
    }

    BaseAuthScreen(showBack = true, onBack = onBack) {

        Text(
            "Verify Code",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier= Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Enter the code we just sent to your registered Email",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "OTP (demo): $generatedOtp",
            color = Color.Blue,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            otpValues.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {

                            //  nếu sai xóa 1 lần ghi lại
                            if (showError) {
                                resetOtp()
                                return@OutlinedTextField
                            }

                            otpValues[index] = newValue

                            if (newValue.isNotEmpty()) {
                                if (index < 5)
                                    focusRequesters[index + 1].requestFocus()
                                else
                                    focusManager.clearFocus()
                            }
                        }
                    },
                    modifier = Modifier
                        .width(48.dp)
                        .focusRequester(focusRequesters[index]),
                    singleLine = true,
                    isError = showError,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
        }

        if (showError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Incorrect verification code",
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val enteredOtp = otpValues.joinToString("")
                if (enteredOtp == generatedOtp) {
                    onNext(enteredOtp)
                } else {
                    showError = true
                }
            },
            enabled = isOtpComplete,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next")
        }
    }
}
