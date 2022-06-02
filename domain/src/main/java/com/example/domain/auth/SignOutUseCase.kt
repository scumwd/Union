package com.example.domain.auth

import com.example.domain.repository.UserRepository

class SignOutUseCase(val userRepository: UserRepository) {

    fun execute(){
        userRepository.signOut()
    }
}