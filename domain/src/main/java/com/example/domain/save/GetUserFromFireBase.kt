package com.example.domain.save

import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetUserFromFireBase(private val userRepository: UserRepository) {

    fun getUser() {
        GlobalScope.launch(Dispatchers.IO){
            val user = userRepository.getUserFirebase()
            userRepository.saveUser(user) {}
        }
    }
}