package com.example.domain.save

import android.util.Log
import com.example.domain.models.User
import com.example.domain.models.UserWithUID
import com.google.firebase.auth.FirebaseAuth

class SaveUserUseCase {

    lateinit var mAuth: FirebaseAuth
    fun execute(user: User){
        mAuth = FirebaseAuth.getInstance()
        mAuth.currentUser
        mAuth.let {
            val userUID = mAuth.uid?.let { it1 ->
                UserWithUID(
                    userId = it1,
                    email = user.email,
                    firstName = user.firstName,
                    lastName = user.lastName,
                    password = user.password)
            }

            userUID?.let { it1 -> Log.e("reg", it1.userId) }
        }
    }
}