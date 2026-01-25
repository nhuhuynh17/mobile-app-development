package com.example.uicomponents

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextDetailScreen() {
    Column(Modifier.padding(24.dp)) {
        Text("Text Component", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        DemoText()
    }
}

@Composable
fun ImageDetailScreen() {
    Column(Modifier.padding(24.dp)) {
        Text("Image Component")
        Spacer(Modifier.height(16.dp))
        DemoImage()
    }
}

@Composable
fun IconDetailScreen() {
    Column(Modifier.padding(24.dp)) {
        Text("Icon Component")
        Spacer(Modifier.height(16.dp))
        DemoIcon()
    }
}

@Composable
fun DividerDetailScreen() {
    Column(Modifier.padding(24.dp)) {
        Text("Divider Component")
        Spacer(Modifier.height(16.dp))
        DemoDivider()
    }
}

@Composable
fun CardDetailScreen() {
    Column(Modifier.padding(24.dp)) {
        Text("Card Component")
        Spacer(Modifier.height(16.dp))
        DemoCard()
    }
}
