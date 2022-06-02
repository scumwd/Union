package com.example.domain.auth

import android.util.Log
import com.example.domain.models.UserDomain
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AuthorizationUseCase(val userRepository: UserRepository) {

    @ExperimentalCoroutinesApi
    suspend fun execute(userDomain: UserDomain): Boolean {
        val userWithUID = userRepository.authorization(userDomain)
        Log.e("tes", userWithUID?.userId.toString())
        return if ( userWithUID != null) {
            GlobalScope.launch(Dispatchers.IO){
                userRepository.insertUserFirebase(userWithUID)
            }
            true
        } else
            false
    }
}