package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.models.UserData
import com.example.data.storage.user.UserStorage
import com.example.domain.models.UserCloudData
import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) :
    UserRepository {
    override fun saveUser(userCloudData: UserCloudData, onSuccess: () -> Unit) {
        val user = UserData(
            userId = userCloudData.getUserId(),
            email = userCloudData.getEmail(),
            firstName = userCloudData.getFirstName(),
            lastName = userCloudData.getLastName()
        )
        userStorage.insert(user)
        onSuccess()
    }

    override fun getUser(userId: String): LiveData<UserWithUID> {

        return userStorage.getUser(userId).map { userDb ->
            UserWithUID(
                userId = userDb.userId,
                email = userDb.email,
                firstName = userDb.firstName,
                lastName = userDb.lastName
            )
        }
    }

}