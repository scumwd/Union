package com.example.union.presentation.screen.register

import androidx.lifecycle.ViewModel
import com.example.domain.auth.AuthorizationUseCase
import com.example.domain.models.UserDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val authorizationUseCase: AuthorizationUseCase
): ViewModel() {

    @ExperimentalCoroutinesApi
    suspend fun addUser(user: UserDomain): Boolean {
        return authorizationUseCase.execute(user)
    }
}