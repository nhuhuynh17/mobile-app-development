package com.example.myapplication.component

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp

@Composable
fun ComponentExample(name: String) {
    when (name) {

        "Button" -> Button(onClick = {}) {
            Text("Click me")
        }

        "Checkbox" -> {
            var checked by remember { mutableStateOf(false) }
            Checkbox(checked = checked, onCheckedChange = {
                checked = it
            })
        }

        "TextField" -> {
            var text by remember { mutableStateOf("") }
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter text") }
            )
        }

        "Slider" -> {
            var value by remember { mutableStateOf(0f) }
            Slider(value = value, onValueChange = { value = it })
        }

        "Switch" -> {
            var checked by remember { mutableStateOf(false) }
            Switch(checked = checked, onCheckedChange = {
                checked = it
            })
        }

        else -> Text(
            text = "Example for $name is not implemented",
            fontSize = 16.sp
        )
    }
}
