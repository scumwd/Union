package com.example.domain.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

class AuthenticationUseCase {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    @ExperimentalCoroutinesApi
    suspend fun logIn(email: String, password: String): Boolean =
        suspendCancellableCoroutine { continuation ->
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
                continuation.resume(task.isSuccessful) {}
            }
        }
}
