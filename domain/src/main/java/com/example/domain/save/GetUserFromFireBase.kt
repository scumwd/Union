package com.example.domain.save

import android.util.Log
import com.example.domain.models.UserCloudData
import com.example.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GetUserFromFireBase(private val userRepository: UserRepository) {

    private val USER_KEY: String = "Users"
    lateinit var mAuth: FirebaseAuth

    fun getUser() {
        mAuth = FirebaseAuth.getInstance()
        val currentUserUID: String
        mAuth.currentUser?.uid.let { currentUserUID = it.toString() }
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