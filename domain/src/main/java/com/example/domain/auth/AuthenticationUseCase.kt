package com.example.domain.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthenticationUseCase {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

     fun logIn(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            task.isSuccessful
        }
    }

}