package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resetpasswordapplication.model.ResetPasswordViewModel
import com.example.resetpasswordapplication.screen.ConfirmScreen
import com.example.resetpasswordapplication.screen.EnterEmailScreen
import com.example.resetpasswordapplication.screen.NewPasswordScreen
import com.example.resetpasswordapplication.screen.VerifyCodeScreen


@Composable
fun ResetPasswordNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: ResetPasswordViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "enter_email",
        modifier = modifier
    ) {

        //Enter Email
        composable("enter_email") {
            EnterEmailScreen(
                viewModel = viewModel,
                onNext = {
                    navController.navigate("verify_code")
                }
            )
        }

        // Verify Code
        composable("verify_code") {
            VerifyCodeScreen(
                onBack = { navController.popBackStack() },
                onNext = { otp ->
                    viewModel.code.value = otp
                    navController.navigate("new_password")
                }
            )
        }

        //  New Password
        composable("new_password") {
            NewPasswordScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = {
                    navController.navigate("confirm")
                }
            )
        }

        //  Confirm
        composable("confirm") {
            ConfirmScreen(
                viewModel,
                onSubmit = {
                    navController.popBackStack(
                        route = "enter_email",
                        inclusive = false
                    )
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
