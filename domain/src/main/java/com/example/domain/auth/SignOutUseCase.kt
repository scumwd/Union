package com.example.domain.auth

import com.google.firebase.auth.FirebaseAuth

class SignOutUseCase {

    lateinit var mAuth: FirebaseAuth

    fun execute(){
        mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
    }
}