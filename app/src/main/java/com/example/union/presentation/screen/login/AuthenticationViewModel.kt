package com.example.union.presentation.screen.login

import androidx.lifecycle.ViewModel
import com.example.domain.auth.AuthenticationUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AuthenticationViewModel : ViewModel() {

    private val authenticationUseCase = AuthenticationUseCase()
    lateinit var mAuth: FirebaseAuth

    @ExperimentalCoroutinesApi
    suspend fun logIn(email: String, password: String): Boolean =
        authenticationUseCase.logIn(email, password)

    fun checkCurrentUser(): Boolean {
        mAuth = FirebaseAuth.getInstance()
        return mAuth.currentUser != null
    }

}