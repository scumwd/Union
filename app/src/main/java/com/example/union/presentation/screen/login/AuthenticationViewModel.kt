package com.example.union.presentation.screen.login

import androidx.lifecycle.ViewModel
import com.example.domain.auth.AuthenticationUseCase
import com.example.domain.auth.CheckCurrentUser
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AuthenticationViewModel(
    private val authenticationUseCase: AuthenticationUseCase,
    private val checkCurrentUser: CheckCurrentUser
): ViewModel() {

    @ExperimentalCoroutinesApi
    suspend fun logIn(email: String, password: String): Boolean =
        authenticationUseCase.logIn(email, password)

    fun checkCurrentUser(): Boolean {
        return checkCurrentUser.execute()
    }

}