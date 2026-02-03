package com.example.uthsmarttasksw07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uthsmarttasksw07.navigation.AppNavHost
import com.example.uthsmarttasksw07.ui.theme.UTHSmartTasksW07Theme
import com.example.uthsmarttasksw07.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTHSmartTasksW07Theme {
                val taskViewModel: TaskViewModel = viewModel()
                AppNavHost(taskViewModel)
            }
        }
    }
}
