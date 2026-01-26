package com.example.productapplication.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.productapplication.viewmodel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    viewModel: ProductViewModel = viewModel(),
    onBackClick: () -> Unit = {}
) {
    val product by viewModel.product.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Product detail")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        product?.let {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                AsyncImage(
                    model = it.imgURL,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp) // 👈 ảnh bự hơn
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = it.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Giá: ${it.price}đ",
                    fontSize = 16.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = it.des,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
