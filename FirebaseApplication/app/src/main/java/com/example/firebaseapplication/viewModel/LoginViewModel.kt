package com.example.firebaseapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.storage.ktx.storage
import com.google.firebase.database.ktx.database
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    // 🔹 Firebase services
    private val auth = FirebaseAuth.getInstance()                 // Auth
    private val analytics = Firebase.analytics                    // Analytics
    private val firestore = Firebase.firestore                    // Firestore
    private val storage = Firebase.storage                        // Storage
    private val crashlytics = Firebase.crashlytics                // Crashlytics
    private val realtimeDb = Firebase.database.reference          // Realtime DB
    private val remoteConfig = Firebase.remoteConfig              // Remote Config

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun firebaseAuthWithGoogle(idToken: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            val credential = GoogleAuthProvider.getCredential(idToken, null)

            auth.signInWithCredential(credential)
                .addOnSuccessListener { result ->

                    val user = result.user

                    // 1️⃣ Firebase Analytics
                    analytics.logEvent("login_success") {
                        param("method", "google")
                    }

                    user?.let {

                        // 2️⃣ Firestore – lưu user
                        val userData = hashMapOf(
                            "uid" to it.uid,
                            "name" to it.displayName,
                            "email" to it.email,
                            "photoUrl" to it.photoUrl.toString()
                        )

                        firestore.collection("users")
                            .document(it.uid)
                            .set(userData)

                        // 3️⃣ Firebase Storage – upload avatar
                        it.photoUrl?.let { photoUri ->
                            storage.reference
                                .child("avatars/${it.uid}.jpg")
                                .putFile(photoUri)
                        }

                        // 4️⃣ Realtime Database – log thời gian login
                        realtimeDb.child("login_logs")
                            .push()
                            .setValue(
                                mapOf(
                                    "uid" to it.uid,
                                    "time" to System.currentTimeMillis()
                                )
                            )
                    }

                    // 5️⃣ Remote Config – fetch (demo sử dụng)
                    remoteConfig.fetchAndActivate()

                    _loginState.value = LoginState.Success
                }
                .addOnFailureListener { e ->

                    // 6️⃣ Firebase Crashlytics – ghi lỗi
                    crashlytics.recordException(e)

                    // 7️⃣ Analytics – log login failed
                    analytics.logEvent("login_failed") {
                        param("error", e.message ?: "unknown")
                    }

                    _loginState.value =
                        LoginState.Error(e.message ?: "Login failed")
                }
        }
    }

    fun signOut() {
        auth.signOut()
    }

    fun setError(message: String) {
        _loginState.value = LoginState.Error(message)
    }
}
