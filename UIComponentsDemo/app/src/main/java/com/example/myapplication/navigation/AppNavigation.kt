package com.example.myapplication.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screen.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // ===== HOME =====
        composable("home") {
            HomeScreen(navController)
        }

        // ===== DETAIL =====
        composable("detail/{component}") { backStackEntry ->
            val component =
                backStackEntry.arguments?.getString("component") ?: ""
            ComponentDetailScreen(
                component = component,
                navController = navController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentDetailScreen(
    component: String,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(component) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            when (component) {
                "Text" -> Text("This is Text component")
                "Image" -> Text("Image component demo")
                "Icon" -> Text("Icon component demo")
                "Divider" -> Divider(thickness = 2.dp)
                "Card" -> Card {
                    Text("This is Card", modifier = Modifier.padding(16.dp))
                }
                else -> Text("Component không tồn tại")
            }
        }
    }
}
