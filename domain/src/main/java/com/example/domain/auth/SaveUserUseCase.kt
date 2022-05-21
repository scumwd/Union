package com.example.domain.auth

import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SaveUserUseCase {
    fun insert(userRepository: UserRepository,userWithUID: UserWithUID, onSuccess: () -> Unit) =
        GlobalScope.launch(Dispatchers.IO) {
            userRepository.saveUser(userWithUID) {
                onSuccess
            }
        }
}