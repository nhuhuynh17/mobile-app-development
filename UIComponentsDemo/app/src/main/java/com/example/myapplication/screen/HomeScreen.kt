package com.example.myapplication.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun HomeScreen(navController: NavController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {
            Text(
                "UI Components List",
                fontSize = 24.sp ,
                color = Color(0xFF3B98DE),
                fontWeight= FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign= TextAlign.Center
            )
            Spacer(Modifier.height(12.dp))
        }
        // DISPLAY
        item {
            Text(
                "Display",
                fontSize = 18.sp,
                fontWeight= FontWeight.Bold
            )
        }
        items(
            listOf("Text", "Image", "Icon", "Divider", "Card")
        ) {
            ComponentItem(it, navController)
        }

        item { Spacer(Modifier.height(12.dp)) }

        //INPUT
        item {
            Text(
                "Input",
                fontSize = 18.sp,
                fontWeight= FontWeight.Bold
            ) }
        items(listOf(
            "Button",
            "OutlinedButton",
            "TextField",
            "Checkbox",
            "Switch",
            "Slider"
        )) {
            ComponentItem(it, navController)
        }

        item { Spacer(Modifier.height(12.dp)) }

        //LAYOUT
        item {
            Text(
                "Layout",
                fontSize = 18.sp,
                fontWeight= FontWeight.Bold
            ) }
        items(listOf("Row", "Column","Box","Spacer")) {
            ComponentItem(it, navController)
        }

        //LIST
        item {
            Text(
                "List/Scroll",
                fontSize = 18.sp,
                fontWeight= FontWeight.Bold
            ) }
        items(listOf("LazyColumn","LazyRow","LazyVerticalGrid")) {
            ComponentItem(it, navController)
        }
    }
}

@Composable
fun ComponentItem(
    name: String,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(
                color = Color(0xFFBCDFFD),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                navController.navigate("detail/$name")
            }
            .padding(12.dp) // giảm padding để text lên cao hơn
    ) {
        Column {
            // 🔹 TEXT TRÊN
            Text(
                text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 🔹 Ô / DÒNG CHỮ DƯỚI
            Text(
                text = getDescription(name),
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}

fun getDescription(name: String): String {
    return when (name) {
        "Text" -> "Display simple text"
        "Image" -> "Show an image"
        "Icon" -> "Display an icon"
        "Divider" -> "Draw divider line"
        "Card" -> "Container with elevation"

        "Button" -> "Clickable button"
        "OutlinedButton" -> "Button with border"
        "TextField" -> "User text input"
        "Checkbox" -> "Select true or false"
        "Switch" -> "Toggle on or off"
        "Slider" -> "Slide to select value"

        "Row" -> "Horizontal layout"
        "Column" -> "Vertical layout"
        "Box" -> "Stack UI elements"
        "Spacer" -> "Add spacing"

        "LazyColumn" -> "Vertical scroll list"
        "LazyRow" -> "Horizontal scroll list"
        "LazyVerticalGrid" -> "Grid scroll list"
        else -> ""
    }

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyApplicationTheme {
        HomeScreen(navController = rememberNavController())
    }
}
