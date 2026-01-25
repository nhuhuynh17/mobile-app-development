package com.example.firebaseapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseapplication.screen.LoginScreen
import com.example.firebaseapplication.screen.ProfileScreen
import com.example.firebaseapplication.ui.theme.FirebaseApplicationTheme
import com.example.firebaseapplication.viewModel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ GOOGLE SIGN IN CLIENT (BẮT BUỘC)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .requestProfile()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        val auth = FirebaseAuth.getInstance()

        setContent {
            FirebaseApplicationTheme {

                val startDestination =
                    if (auth.currentUser != null) "profile" else "login"

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {

                    composable("login") {

                        val loginViewModel: LoginViewModel =
                            androidx.lifecycle.viewmodel.compose.viewModel()

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
                                auth.signOut()
                                googleSignInClient.signOut()

                                navController.navigate("login") {
                                    popUpTo("profile") { inclusive = true }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
