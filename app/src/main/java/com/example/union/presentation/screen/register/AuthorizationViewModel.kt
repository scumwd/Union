package com.example.union.presentation.screen.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.domain.auth.AuthorizationUseCase
import com.example.domain.models.UserDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AuthorizationViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    lateinit var authorizationUseCase: AuthorizationUseCase

    @ExperimentalCoroutinesApi
    suspend fun addUser(user: UserDomain): Boolean {
        authorizationUseCase = AuthorizationUseCase()
        return authorizationUseCase.execute(user)
    }
}