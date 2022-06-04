package com.example.domain.save

import com.example.domain.repository.UserRepository

class GetUserFromFireBase(private val userRepository: UserRepository) {

    suspend fun getUser(): Boolean {
        val user = userRepository.getUserFirebase()
        userRepository.saveUser(user) {}
        return true
    }
}