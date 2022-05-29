package com.example.domain.cloud

import com.example.domain.models.UserWithUID
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserInsertCloud {

    private val USER_KEY: String = "Users"

    fun insert(userWithUID: UserWithUID) {
        val database = Firebase.database
        val myRef = database.getReference(USER_KEY)
        myRef.child(userWithUID.userId).setValue(userWithUID)
    }
}