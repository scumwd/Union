package com.example.data.storage.user

import com.example.data.models.User

interface UserStorage {

    fun save(user: User): Boolean

    fun get(userId: String): User
}