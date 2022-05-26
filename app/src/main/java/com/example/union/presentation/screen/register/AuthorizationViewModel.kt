package com.example.union.presentation.screen.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.data.repository.UserRepositoryImpl
import com.example.data.storage.user.UserStorageImpl
import com.example.domain.auth.AuthorizationUseCase
import com.example.domain.models.UserDomain
import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext

class AuthorizationViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    lateinit var authorizationUseCase: AuthorizationUseCase

    @ExperimentalCoroutinesApi
    suspend fun addUser(user: UserDomain) : Boolean{
        val daoUser = UserStorageImpl.getInstance(context).getUserDao()
        val userRepository: UserRepository = UserRepositoryImpl(daoUser)
        authorizationUseCase = AuthorizationUseCase(userRepository)
        return authorizationUseCase.execute(user)


    }

    fun getUser(userId: String): LiveData<UserWithUID> {
        val daoUser = UserStorageImpl.getInstance(context).getUserDao()
        val userRepository: UserRepository = UserRepositoryImpl(daoUser)
        return userRepository.getUser(userId)
    }
}