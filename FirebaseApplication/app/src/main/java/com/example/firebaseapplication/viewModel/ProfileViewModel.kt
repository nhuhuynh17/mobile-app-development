package com.example.firebaseapplication.viewModel

import androidx.lifecycle.ViewModel
import com.example.firebaseapplication.model.UserModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class ProfileViewModel : ViewModel() {

    // Firebase Realtime Database
    private val dbRef = FirebaseDatabase.getInstance().getReference("users")

    // Firebase Auth
    private val auth = FirebaseAuth.getInstance()

    // Firebase Firestore
    private val firestore = FirebaseFirestore.getInstance()

    // Firebase Storage
    private val storage = FirebaseStorage.getInstance()

    fun saveUserToRealtimeDB(user: UserModel) {
        dbRef.child(user.uid).setValue(user)
    }

    fun saveUserToFirestore(user: UserModel) {
        firestore.collection("users").document(user.uid).set(user)
    }

    fun uploadUserAvatar(userId: String, bytes: ByteArray) {
        val ref = storage.reference.child("avatars/$userId.jpg")
        ref.putBytes(bytes)
    }

    fun getCurrentUserId(): String? = auth.currentUser?.uid
}
