package com.example.union.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.AuthenticationUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext

class AuthenticationViewModel : ViewModel() {

    private val authenticationUseCase = AuthenticationUseCase()

    @ExperimentalCoroutinesApi
    suspend fun logIn(email: String, password: String): Boolean =
        authenticationUseCase.logIn(email, password)

}