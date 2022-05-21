package com.example.domain.save

import android.util.Log
import com.example.domain.models.UserDomain
import com.example.domain.models.UserWithUID
import com.google.firebase.auth.FirebaseAuth

class SaveUserUseCase {

    lateinit var mAuth: FirebaseAuth
    fun execute(userDomain: UserDomain){
        mAuth = FirebaseAuth.getInstance()
        mAuth.currentUser
        mAuth.let {
            val userUID = mAuth.uid?.let { it1 ->
                UserWithUID(
                    userId = it1,
                    email = userDomain.email,
                    firstName = userDomain.firstName,
                    lastName = userDomain.lastName,
                    password = userDomain.password)
            }

            userUID?.let { it1 -> Log.e("reg", it1.userId) }
        }
    }
}