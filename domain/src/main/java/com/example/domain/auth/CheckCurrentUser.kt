package com.example.domain.auth

import com.example.domain.repository.UserRepository

class CheckCurrentUser(val userRepository: UserRepository) {

    fun execute(): Boolean{
        return userRepository.currentUser() != null
    }

}