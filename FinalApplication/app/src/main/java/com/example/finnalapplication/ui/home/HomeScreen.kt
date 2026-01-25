package com.example.finnalapplication.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finnalapplication.data.model.Task
import com.example.finnalapplication.navigation.Screen
import com.example.finnalapplication.ui.theme.TaskColors

@Composable
fun HomeScreen(
    navController: NavController,
    vm: HomeViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        if (vm.tasks.isEmpty()) {
            vm.loadTasks()
        }
    }


    val deletedTaskId =
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.get<Int>("deletedTaskId")

    LaunchedEffect(deletedTaskId) {
        deletedTaskId?.let {
            vm.removeTask(it)
            navController.currentBackStackEntry
                ?.savedStateHandle
                ?.remove<Int>("deletedTaskId")
        }
    }


    //  Điều hướng khi không có task
    LaunchedEffect(vm.isLoading, vm.tasks.size) {
        if (!vm.isLoading && vm.tasks.isEmpty()) {
            navController.navigate(Screen.Empty.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            }
        }
    }

    when {
        vm.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        vm.tasks.isNotEmpty() -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(vm.tasks) { task ->
                    TaskItem(
                        task = task,
                        onClick = {
                            navController.navigate(
                                Screen.Detail.createRoute(task.id)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = TaskColors[task.id % TaskColors.size]
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Title
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Description
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Status
            Text(
                text = "Status: ${task.status}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black.copy(alpha = 0.7f)
            )
        }
    }
}
