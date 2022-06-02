package com.example.domain.cloud

import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserInsertCloud(val userRepository: UserRepository) {

    fun insert(userWithUID: UserWithUID) {
        GlobalScope.launch(Dispatchers.IO){
            userRepository.insertUserFirebase(userWithUID)
        }
    }
}