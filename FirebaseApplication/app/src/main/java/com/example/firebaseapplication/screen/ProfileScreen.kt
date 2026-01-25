package com.example.firebaseapplication.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

@Composable
fun ProfileScreen(
    onLogout: () -> Unit
) {
    // Firebase Authentication
    val user = FirebaseAuth.getInstance().currentUser

    //  Firebase services
    val firestore = FirebaseFirestore.getInstance()
    val analytics = Firebase.analytics
    val crashlytics = FirebaseCrashlytics.getInstance()
    val remoteConfig = Firebase.remoteConfig

    val name = user?.displayName ?: "No name"
    val email = user?.email ?: "No email"
    val photoUrl = user?.photoUrl?.toString()

    var welcomeText by remember { mutableStateOf("Welcome") }

    //  Analytics +  Remote Config
    LaunchedEffect(Unit) {

        // Firebase Analytics
        analytics.logEvent("open_profile_screen", null)

        // Firebase Remote Config
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            }
        )

        remoteConfig.setDefaultsAsync(
            mapOf("welcome_text" to "Welcome to Firebase App")
        )

        remoteConfig.fetchAndActivate()
            .addOnSuccessListener {
                welcomeText = remoteConfig.getString("welcome_text")
            }
    }

    // 🗄️ Firestore – lưu thông tin user
    LaunchedEffect(user) {
        user?.let {
            firestore.collection("users")
                .document(it.uid)
                .set(
                    mapOf(
                        "uid" to it.uid,
                        "name" to name,
                        "email" to email,
                        "photoUrl" to photoUrl
                    )
                )
                .addOnFailureListener { e ->
                    // Firebase Crashlytics
                    crashlytics.recordException(e)
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        // 👤 Avatar (Firebase Storage – thông qua Google photoUrl)
        if (photoUrl != null) {
            AsyncImage(
                model = photoUrl,
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
        } else {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Avatar",
                modifier = Modifier.size(120.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        //  Remote Config text
        Text(
            text = welcomeText,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = name, style = MaterialTheme.typography.headlineSmall)
        Text(text = email)

        Spacer(modifier = Modifier.height(24.dp))

        //  Firebase user info
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Firebase User Information", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("UID: ${user?.uid}")
                Text("Provider: Google")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        //  HIỂN THỊ RÕ 7 TIỆN ÍCH FIREBASE
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = "Firebase Features Used",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Firebase Authentication (Google Sign-In)")
                Text("Cloud Firestore (User data storage)")
                Text(" Firebase Analytics (Screen & event tracking)")
                Text(" Firebase Remote Config (Dynamic welcome text)")
                Text("Firebase Crashlytics (Error logging)")
                Text("Firebase Storage (User avatar)")
                Text(" Google Identity Provider")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Logout
        Button(
            onClick = {
                FirebaseAuth.getInstance().signOut()
                onLogout()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }

        Spacer(modifier = Modifier.height(16.dp))

        //  Delete account
        Button(
            onClick = {
                user?.delete()?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onLogout()
                    } else {
                        crashlytics.recordException(
                            task.exception ?: Exception("Delete account failed")
                        )
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete Account")
        }
    }
}
