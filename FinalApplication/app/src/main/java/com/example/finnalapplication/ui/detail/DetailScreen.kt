package com.example.finnalapplication.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finnalapplication.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    taskId: Int,
    vm: DetailViewModel = viewModel()
) {

    LaunchedEffect(taskId) {
        vm.loadDetail(taskId)
    }

    // Nếu không có task → Empty
    LaunchedEffect(vm.isLoading, vm.task) {
        if (!vm.isLoading && vm.task == null) {
            navController.navigate(Screen.Empty.route) {
                popUpTo(Screen.Home.route)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail", fontWeight = FontWeight.Medium) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .background(Color(0xFFE3F2FD), CircleShape)
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color(0xFF1976D2)
                        )
                    }
                },
                actions = {
                    IconButton(
                        enabled = vm.task != null,
                        onClick = {
                            vm.deleteTask(taskId) {

                                // BÁO CHO HOME BIẾT TASK ĐÃ XOÁ
                                navController.previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("deletedTaskId", taskId)

                                navController.popBackStack()
                            }
                        },
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .background(Color(0xFFFFE0B2), CircleShape)
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = null,
                            tint = Color(0xFFF57C00)
                        )
                    }
                }
            )
        }
    ) { padding ->

        if (vm.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        vm.task?.let { task ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {

                // TITLE
                Text(
                    text = task.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                // DESCRIPTION
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(Modifier.height(16.dp))

                // INFO CARD
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFEAEA)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        InfoItem(
                            title = "Category",
                            value = "Work",
                            modifier = Modifier.weight(1f)
                        )
                        InfoItem(
                            title = "Status",
                            value = "In Progress",
                            modifier = Modifier.weight(1f)
                        )
                        InfoItem(
                            title = "Priority",
                            value = "High",
                            modifier = Modifier.weight(1f)
                        )
                    }

                }

                Spacer(Modifier.height(20.dp))

                // SUBTASKS
                Text("Subtasks", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))

                repeat(3) {
                    SubtaskItem("This task is related to Fitness. It needs to be completed")
                }

                Spacer(Modifier.height(20.dp))

                // ATTACHMENTS
                Text("Attachments", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))

                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.AttachFile, null)
                        Spacer(Modifier.width(8.dp))
                        Text("document_1_0.pdf")
                    }
                }
            }
        }
    }
}


@Composable
fun InfoItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            color = Color.Gray
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun SubtaskItem(text: String) {
    var checked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }
        )
        Text(text)
    }
}
