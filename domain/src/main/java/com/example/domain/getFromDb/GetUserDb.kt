package com.example.domain.getFromDb

import androidx.lifecycle.LiveData
import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class GetUserDb(val userRepository: UserRepository) {

    lateinit var mAuth: FirebaseAuth

    fun execute(): LiveData<UserWithUID> {
        mAuth = FirebaseAuth.getInstance()
        val userId: String
        mAuth.currentUser?.uid.let { userId = it.toString() }
        return userRepository.getUser(userId)
    }
}