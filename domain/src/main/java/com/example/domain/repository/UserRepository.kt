package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.models.UserDomain
import com.example.domain.models.UserWithUID

interface UserRepository {

    fun getUser(): LiveData<UserWithUID>

    suspend fun getUsersById(userId: String) : UserWithUID

    suspend fun saveUser(userCloudData: List<UserWithUID?>, onSuccess: () -> Unit)

    fun signOut()

    suspend fun authentication(email: String, password: String): Boolean

    suspend fun authorization(userDomain: UserDomain) : UserWithUID?

    fun currentUser(): String?

    suspend fun insertUserFirebase(userWithUID: UserWithUID)

    suspend fun getUserFirebase() : List<UserWithUID?>

}