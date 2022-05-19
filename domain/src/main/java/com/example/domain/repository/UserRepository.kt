package com.example.domain.repository

import com.example.domain.models.UserWithUID

interface UserRepository {

    fun saveUser(userWithUID: UserWithUID): Boolean

    fun getUser(userId: String): UserWithUID
}