package com.example.myuiapplication.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myuiapplication.R

@Composable
fun MainScreen(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id=R.drawable.avatar),
            contentDescription = "user avatar",
            modifier=Modifier.size(200.dp)
                .clip(CircleShape)
//         .clip(RoundedCornerShape(16.dp))
//            hoặc .clip(CircleShape)
//                    .border(
//                        width = 3.dp,
//                        color = MaterialTheme.colorScheme.primary,
//                        shape = CircleShape
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text="VietNam",
            fontWeight = FontWeight.Bold,
           fontSize=32.sp
        )

        Spacer(modifier=Modifier.height(12.dp))

        Text(text = "Phan Thi Nhu Huynh",
            fontSize =24.sp
        )
    }
}