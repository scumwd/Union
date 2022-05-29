package com.example.domain.save

import android.util.Log
import com.example.domain.models.UserCloudData
import com.example.domain.repository.UserRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GetUserFromFireBase {

    private val USER_KEY: String = "Users"

    fun getUser(userRepository: UserRepository, currentUserUID: String) {
        val rootRef = FirebaseDatabase.getInstance().reference
        val messageRef = rootRef.child(USER_KEY).child(currentUserUID)
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user: UserCloudData? = dataSnapshot.getValue(UserCloudData::class.java)
                user?.let { userRepository.saveUser(it) {} }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("firebaseErrorUser", error.message)
            }
        }
        messageRef.addListenerForSingleValueEvent(valueEventListener)
    }
}