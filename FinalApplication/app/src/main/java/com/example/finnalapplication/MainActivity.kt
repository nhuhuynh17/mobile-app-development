package com.example.finnalapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.finnalapplication.navigation.NavGraph
import com.example.finnalapplication.navigation.Screen
import com.example.finnalapplication.ui.main.MainScreen
import com.example.finnalapplication.ui.theme.FinnalApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = {
                    val route =
                        navController.currentBackStackEntryAsState().value
                            ?.destination?.route

                    if (route == Screen.Home.route) {
                        BottomBar()
                    }
                }
            ) { padding ->
                NavGraph(
                    navController = navController,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@Composable
fun BottomBar() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )
    }
}
