package com.example.firebaseapplication.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseapplication.screen.LoginScreen
import com.example.firebaseapplication.screen.ProfileScreen
import com.example.firebaseapplication.viewModel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavGraph(
    googleSignInClient: GoogleSignInClient
) {
    val navController = rememberNavController()

    // 🔥 Auto-login nếu đã đăng nhập
    val startDestination =
        if (FirebaseAuth.getInstance().currentUser != null)
            "profile"
        else
            "login"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable("login") {
            val loginViewModel: LoginViewModel = viewModel()

            LoginScreen(
                viewModel = loginViewModel,
                googleSignInClient = googleSignInClient,
                onLoginSuccess = {
                    navController.navigate("profile") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("profile") {

            ProfileScreen(
                onLogout = {
                    FirebaseAuth.getInstance().signOut()
                    googleSignInClient.signOut()

                    navController.navigate("login") {
                        popUpTo("profile") { inclusive = true }
                    }
                }
            )
        }
    }
}
