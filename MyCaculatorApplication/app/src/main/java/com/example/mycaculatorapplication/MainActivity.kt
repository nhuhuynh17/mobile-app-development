package com.example.mycaculatorapplication

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycaculatorapplication.ui.theme.MyCaculatorApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCaculatorApplicationTheme {
                Calculator()
            }
        }
    }
}

@Composable
fun Calculator() {
    var firstnumber by remember { mutableStateOf("") }
    var secondnumber by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "BAI THUC HANH SO 03",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value= firstnumber,
            onValueChange ={
                firstnumber=it
                error=false
            },
            label = {Text("nhap so thu nhat")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            listOf("+", "-", "*", "÷").forEach { op ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .background(color = getColorbyOperator(op),
                            RoundedCornerShape(12.dp))
                        .clickable {
                            val a = firstnumber.toDoubleOrNull()
                            val b = secondnumber.toDoubleOrNull()

                            if (a == null || b == null) {
                                error = true
                                result = ""
                            } else {
                                error = false
                                result = when (op) {
                                    "+" -> (a + b).toString()
                                    "-" -> (a - b).toString()
                                    "*" -> (a * b).toString()
                                    "÷" -> {
                                        if (b == 0.0) {
                                            error = true
                                            ""
                                        } else {
                                            (a / b).toString()
                                        }
                                    }
                                    else -> ""
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = op,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value= secondnumber,
            onValueChange ={
                secondnumber=it
                error=false
            },
            label = {Text("nhap so thu hai")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        //error
        if(error) {
            Text(
                text = "Dữ liệu không hợp lệ",
                color = Color.Red
            )
        }

        // result
        if(result.isNotEmpty()) {
            Text(
                text = " Result: $result",
                fontSize= 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }

    }
}

fun getColorbyOperator(op:String):Color {
    return when(op){
        "+"->Color.Red
        "-"->Color.Green
        "*"->Color.Yellow
        "÷"->Color.Gray
        else -> Color.Blue
    }
}