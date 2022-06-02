package com.example.domain.auth

import com.example.domain.models.UserDomain
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AuthorizationUseCase(val userRepository: UserRepository) {

    @ExperimentalCoroutinesApi
    suspend fun execute(userDomain: UserDomain): Boolean {
        val userWithUID = userRepository.authorization(userDomain)
        return if ( userWithUID != null) {
            userRepository.saveUser(userWithUID) {}
            true
        } else
            false
    }
}