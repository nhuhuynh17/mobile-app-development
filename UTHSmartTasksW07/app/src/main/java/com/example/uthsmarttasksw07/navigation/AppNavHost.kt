package com.example.uthsmarttasksw07.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasksw07.screen.AddTaskScreen
import com.example.uthsmarttasksw07.screen.TaskListScreen
import com.example.uthsmarttasksw07.viewmodel.TaskViewModel

@Composable
fun AppNavHost(
    viewModel: TaskViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            TaskListScreen(
                viewModel = viewModel,
                onAddClick = {
                    navController.navigate("add")
                }
            )
        }
        composable("add") {
            AddTaskScreen(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
