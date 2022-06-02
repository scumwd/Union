package com.example.data.storage.user

import com.example.data.models.UserCloudData
import com.example.domain.models.UserDomain
import com.example.domain.models.UserWithUID

interface UserFirebase {

    suspend fun authentication(email: String, password: String): Boolean

    suspend fun authorization(userDomain: UserDomain) : UserWithUID?

    fun currentUser(): String?

    fun signOut()

    suspend fun insertUser(userWithUID: UserWithUID)

    suspend fun getUsers(): UserCloudData?
}