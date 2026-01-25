package com.example.resetpasswordapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.resetpasswordapplication.R


@Composable
fun BaseAuthScreen(
    showBack:Boolean = false,
    onBack : (()->Unit)?=null,
    content : @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        if (showBack && onBack != null) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.focusable(false)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.outline_arrow_back_24),
                    contentDescription = "Back"
                )

            }
        } else {
            Spacer(modifier = Modifier.height(48.dp))
        }

        Spacer(modifier=Modifier.height(16.dp))

        Image(
            painter = painterResource(id=R.drawable.uth_logo),
            contentDescription = "UTH Logo",
            modifier= Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier=Modifier.height(8.dp))

        Text(
            text="SmartStasks",
            style= MaterialTheme.typography.titleLarge,
            color= Color(0xFF418CD9),
           modifier= Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier=Modifier.height(32.dp))

        content()

    }
}