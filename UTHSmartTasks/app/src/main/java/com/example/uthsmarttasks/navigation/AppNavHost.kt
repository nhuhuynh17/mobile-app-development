package com.example.uthsmarttasks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.data.OnboardingData
import com.example.uthsmarttasks.screen.OnboardingScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val totalPages = OnboardingData.items.size

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Onboarding1.route
    ) {

        // Onboarding 1
        composable(ScreenRoute.Onboarding1.route) { backStackEntry ->
            val pageIndex = 0
            OnboardingScreen(
                item = OnboardingData.items[pageIndex],
                pageIndex = pageIndex,
                totalPages = totalPages,
                onNext = {
                    navController.navigate(ScreenRoute.Onboarding2.route)
                },
                onBack = {
                    // màn hình đầu tiên không có Back, có thể để trống
                },
                onSkip = {
                    navController.navigate(ScreenRoute.Onboarding3.route) {
                        popUpTo(ScreenRoute.Onboarding1.route) { inclusive = true }
                    }
                },
                isLast = pageIndex == totalPages - 1
            )
        }

        // Onboarding 2
        composable(ScreenRoute.Onboarding2.route) { backStackEntry ->
            val pageIndex = 1
            OnboardingScreen(
                item = OnboardingData.items[pageIndex],
                pageIndex = pageIndex,
                totalPages = totalPages,
                onNext = {
                    navController.navigate(ScreenRoute.Onboarding3.route)
                },
                onBack = {
                    navController.popBackStack()
                },
                onSkip = {
                    navController.navigate(ScreenRoute.Onboarding3.route) {
                        popUpTo(ScreenRoute.Onboarding1.route) { inclusive = true }
                    }
                },
                isLast = pageIndex == totalPages - 1
            )
        }

        // Onboarding 3
        composable(ScreenRoute.Onboarding3.route) { backStackEntry ->
            val pageIndex = 2
            OnboardingScreen(
                item = OnboardingData.items[pageIndex],
                pageIndex = pageIndex,
                totalPages = totalPages,
                onNext = {

                },
                onBack = {
                    navController.popBackStack()
                },
                onSkip = {

                },
                isLast = pageIndex == totalPages - 1
            )
        }
    }
}
