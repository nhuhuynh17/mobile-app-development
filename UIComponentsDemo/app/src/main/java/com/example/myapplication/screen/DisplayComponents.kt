package com.example.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

// ==================== DISPLAY COMPONENTS ====================

@Composable
fun DemoText() {
    Text(text = "This is a Text component")
}

@Composable
fun DemoImage() {
    Image(
        painter = painterResource(id = R.drawable.uth),
        contentDescription = "Sample Image",
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun DemoIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Sample Icon",
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun DemoDivider() {
    Divider(thickness = 2.dp)
}

@Composable
fun DemoCard() {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Text(
            text = "This is a Card",
            modifier = Modifier.padding(16.dp)
        )
    }
}
