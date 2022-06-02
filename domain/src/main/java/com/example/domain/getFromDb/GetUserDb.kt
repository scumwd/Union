package com.example.domain.getFromDb

import androidx.lifecycle.LiveData
import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository

class GetUserDb(val userRepository: UserRepository) {

    fun execute(): LiveData<UserWithUID> {
        return userRepository.getUser()
    }
}