package com.example.numberapplication2

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.contentType
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberapplication2.ui.theme.NumberApplication2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumberApplication2Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var input by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }
    var number by remember { mutableStateOf(0) }

    Column(
        modifier=Modifier.fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text="Bai thuc hanh 02 (demo)",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier=Modifier.height(24.dp))

       Row() {
           TextField(
               value = input,
               onValueChange = {
                   input = it
                   error = false
               },
               label = {Text("Nhap vao so luong")},
               keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
               modifier = Modifier.weight(1f)
           )

           Spacer(modifier=Modifier.width(12.dp))

           Button(onClick= {
               val n = input.toIntOrNull()
               if (n == null || n<=0) {
                   error=true
                   number=0
               }else{
                   number = n
               }
           }) {
               Text("Tao")
           }
       }
        Spacer(modifier=Modifier.height(12.dp))

        // error
        if (error){
            Text("Du lieu nhap khong hop le", color= Color.Red)
        }
        Spacer(modifier=Modifier.height(24.dp))

        Column() {
           for (i in 1..number) {
               Box(
                   modifier = Modifier.fillMaxWidth()
                       .padding(2.dp)
                       .clip(CircleShape)
                       .background(color = Color.Red),
                   contentAlignment = Alignment.Center
               ) {
                   Text(
                       text= i.toString(),
                       color=Color.White,
                       fontSize = 18.sp
                   )
               }

               Spacer(modifier=Modifier.height(8.dp))
           }
        }
    }
}