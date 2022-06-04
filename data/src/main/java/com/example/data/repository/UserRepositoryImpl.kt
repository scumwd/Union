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

class UserRepositoryImpl(
    private val userStorage: UserStorage,
    private val userFirebase: UserFirebase
) :
    UserRepository {

    lateinit var mAuth: FirebaseAuth

    override suspend fun saveUser(userCloudData: List<UserWithUID?>, onSuccess: () -> Unit) {

        val listUsers = userCloudData.map {
            UserRoom(
                userId = it?.userId.toString(),
                firstName = it?.firstName.toString(),
                lastName = it?.lastName.toString(),
                email = it?.email.toString()
            )
        }
       listUsers.forEach {
           userStorage.insert(it)
       }
        onSuccess()
    }

    override fun signOut() {
        userFirebase.signOut()
    }

    override suspend fun authentication(email: String, password: String): Boolean {
        return userFirebase.authentication(email, password)
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

    override suspend fun getUserFirebase(): List<UserWithUID?> {
        val userCloud = userFirebase.getUsers()
        return userCloud.map {
            UserWithUID(
                userId = it?.getUserId().toString(),
                firstName = it?.getFirstName().toString(),
                lastName = it?.getLastName().toString(),
                email = it?.getEmail().toString()
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

    override suspend fun getUsersById(userId: String): UserWithUID {
        return userStorage.getUserById(userId).let {
            UserWithUID(
                userId = it.userId,
                firstName = it.firstName,
                lastName = it.lastName,
                email = it.email
            )
        }
    }

}