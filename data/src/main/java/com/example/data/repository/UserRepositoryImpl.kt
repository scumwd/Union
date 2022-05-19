package com.example.data.repository

import com.example.data.models.User
import com.example.data.storage.user.UserStorage
import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage): UserRepository {
    override fun saveUser(userWithUID: UserWithUID): Boolean {
        val user = User(userId = userWithUID.userId, email = userWithUID.email, firstName = userWithUID.firstName, lastName = userWithUID.lastName, password = userWithUID.password)
        val result = userStorage.save(user)
        return result
    }

    override fun getUser(userId: String): UserWithUID {
        val user = userStorage.get(userId = userId)
        val userWithUID = UserWithUID(userId = user.userId, email = user.email, firstName = user.firstName, lastName = user.lastName, password = user.password)
        return userWithUID
    }
}