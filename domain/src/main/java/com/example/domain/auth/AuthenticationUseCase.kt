package com.example.domain.auth

import com.example.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AuthenticationUseCase(val userRepository: UserRepository) {


    @ExperimentalCoroutinesApi
    suspend fun logIn(email: String, password: String): Boolean = userRepository.authentication(email,password)

}
