package com.example.union.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.auth.AuthorizationUseCase

class AuthorizationViewModelFactory(
    val authorizationUseCase: AuthorizationUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthorizationViewModel(
            authorizationUseCase = authorizationUseCase
        ) as T
    }

}