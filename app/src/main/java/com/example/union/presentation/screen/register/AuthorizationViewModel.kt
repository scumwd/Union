package com.example.union.presentation.screen.register

import androidx.lifecycle.ViewModel
import com.example.domain.auth.AuthorizationUseCase
import com.example.domain.models.UserDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AuthorizationViewModel(
    private val authorizationUseCase: AuthorizationUseCase
): ViewModel() {

    @ExperimentalCoroutinesApi
    suspend fun addUser(user: UserDomain): Boolean {
        return authorizationUseCase.execute(user)
    }
}