package com.example.union.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.auth.AuthenticationUseCase
import com.example.domain.auth.CheckCurrentUser
import com.example.domain.save.GetUserFromFireBase

class AuthenticationViewModelFactory(
    val authenticationUseCase: AuthenticationUseCase,
    val getUser: GetUserFromFireBase,
    val checkCurrentUser: CheckCurrentUser
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthenticationViewModel(
            authenticationUseCase = authenticationUseCase,
            getUser = getUser,
            checkCurrentUser = checkCurrentUser
        ) as T

    }
}