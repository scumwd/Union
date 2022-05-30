package com.example.domain.auth

import com.google.firebase.auth.FirebaseAuth

class CheckCurrentUser {

    lateinit var mAuth: FirebaseAuth

    fun execute(): Boolean{
        mAuth = FirebaseAuth.getInstance()
        return mAuth.currentUser != null
    }

}