package com.example.union.presentation.screen.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.data.repository.UserRepositoryImpl
import com.example.data.storage.user.UserStorageImpl
import com.example.domain.auth.AuthorizationUseCase
import com.example.domain.models.UserDomain
import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository
import com.example.union.presentation.USER_REPOSITORY

class AuthorizationViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    lateinit var authorizationUseCase: AuthorizationUseCase

    fun addUser(user: UserDomain) {
        val daoUser = UserStorageImpl.getInstance(context).getUserDao()
        val userrep: UserRepository = UserRepositoryImpl(daoUser)
        authorizationUseCase = AuthorizationUseCase(userrep)
        authorizationUseCase.execute(user,context)

    }

    fun getUser(userId: String): LiveData<UserWithUID> {
        val daoUser = UserStorageImpl.getInstance(context).getUserDao()
        val userrep: UserRepository = UserRepositoryImpl(daoUser)
        return userrep.getUser(userId)
    }
}