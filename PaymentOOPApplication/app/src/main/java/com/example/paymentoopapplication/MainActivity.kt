package com.example.paymentoopapplication

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.paymentoopapplication.ui.theme.PaymentOOPApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaymentOOPApplicationTheme {
               PaymentScreen()
            }
        }
    }
}

@Composable
fun PaymentScreen() {
    var selectedMethod by remember {
        mutableStateOf<PaymentMethod?>(null)
    }

    val methods = listOf(
        PaymentMethod.PayPal,
        PaymentMethod.GooglePay,
        PaymentMethod.ApplePay
    )

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    selectedMethod?.icon ?: R.drawable.wallet
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.6f), // 👈 chỉnh ở đây
                contentScale = ContentScale.Inside
            )
        }


        Spacer(modifier = Modifier.height(12.dp))

        Divider(
            thickness = 2.dp,
            color = Color.Gray
        )


        methods.forEach { method ->
            MethodItem(
                method = method,
                selected = selectedMethod == method,
                onSelect = { selectedMethod = method }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /* Continue */ },
            enabled = selectedMethod != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}


@Composable
fun MethodItem(
    method: PaymentMethod,
    selected: Boolean,
    onSelect: ()-> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = {onSelect()}
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = method.title,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier.size(56.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(method.icon),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

    }
}