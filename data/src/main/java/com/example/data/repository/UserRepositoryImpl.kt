package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.models.UserRoom
import com.example.data.storage.user.UserFirebase
import com.example.data.storage.user.UserStorage
import com.example.data.models.UserCloudData
import com.example.domain.models.UserDomain
import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class UserRepositoryImpl(private val userStorage: UserStorage, private val userFirebase: UserFirebase) :
    UserRepository {

    lateinit var mAuth: FirebaseAuth

    override fun saveUser(userCloudData: UserWithUID?, onSuccess: () -> Unit) {
        val user = userCloudData?.let {
            UserRoom(
                userId = userCloudData.userId,
                email = userCloudData.email,
                firstName = userCloudData.firstName,
                lastName = it.lastName
            )
        }
        if (user != null) {
            userStorage.insert(user)
        }
        onSuccess()
    }

    override fun signOut() {
        userFirebase.signOut()
    }

    override suspend fun authentication(email: String, password: String): Boolean {
        return userFirebase.authentication(email,password)
    }

    override suspend fun authorization(userDomain: UserDomain): UserWithUID? {
        return userFirebase.authorization(userDomain)
    }

    override fun currentUser(): String? {
        return userFirebase.currentUser()
    }

    override suspend fun insertUserFirebase(userWithUID: UserWithUID) {
        userFirebase.insertUser(userWithUID)
    }

    override suspend fun getUserFirebase() : UserWithUID? {
        val userCloud = userFirebase.getUsers()
        return userCloud?.let {
            UserWithUID(
                userId = it.getUserId(),
                email = userCloud.getEmail(),
                lastName = userCloud.getLastName(),
                firstName = userCloud.getFirstName()
            )
        }
    }

    override fun getUser(): LiveData<UserWithUID> {
        mAuth = FirebaseAuth.getInstance()
        val userId: String = mAuth.currentUser?.uid.toString()
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