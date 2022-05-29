package com.example.union.presentation.screen.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.data.repository.UserRepositoryImpl
import com.example.data.storage.user.UserStorageImpl
import com.example.domain.auth.AuthenticationUseCase
import com.example.domain.save.GetUserFromFireBase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    private val authenticationUseCase = AuthenticationUseCase()
    private val getUserFromFireBase = GetUserFromFireBase()
    lateinit var mAuth: FirebaseAuth

    @ExperimentalCoroutinesApi
    suspend fun logIn(email: String, password: String): Boolean =
        authenticationUseCase.logIn(email, password)

    fun checkCurrentUser(): Boolean {
        mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
        return mAuth.currentUser != null
    }

    fun getUserFromFireBase() {
        mAuth = FirebaseAuth.getInstance()
        val userId = mAuth.currentUser?.uid.toString()
        Log.e("userId", userId)
        val daoUser = UserStorageImpl.getInstance(context).getUserDao()
        val userRepository = UserRepositoryImpl(daoUser)
        getUserFromFireBase.getUser(userRepository, userId)
    }

}