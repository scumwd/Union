package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.models.UserCloudData
import com.example.domain.models.UserWithUID

interface UserRepository {

    fun getUser(userId: String): LiveData<UserWithUID>

    fun saveUser(userCloudData: UserCloudData, onSuccess: () -> Unit)

}