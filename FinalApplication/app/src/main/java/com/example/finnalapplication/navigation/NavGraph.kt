package com.example.finnalapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.finnalapplication.ui.detail.DetailScreen
import com.example.finnalapplication.ui.empty.EmptyScreen
import com.example.finnalapplication.ui.home.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Empty : Screen("empty")
    object Detail : Screen("detail/{taskId}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Empty.route) {
            EmptyScreen(navController)
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("taskId") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val taskId =
                backStackEntry.arguments?.getInt("taskId") ?: return@composable

            DetailScreen(
                navController = navController,
                taskId = taskId
            )
        }
    }
}
