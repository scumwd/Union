package com.example.data.storage.user

import android.util.Log
import com.example.data.USER_KEY
import com.example.data.models.UserCloudData
import com.example.domain.models.UserDomain
import com.example.domain.models.UserWithUID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine

class UserFirebaseImpl: UserFirebase {

    lateinit var mAuth: FirebaseAuth
    override suspend fun authentication(email: String, password: String): Boolean {
        mAuth = FirebaseAuth.getInstance()
        return suspendCancellableCoroutine { continuation ->
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                continuation.resume(task.isSuccessful) {}
            }
        }
    }

    override suspend fun authorization(userDomain: UserDomain): UserWithUID? {
        mAuth = FirebaseAuth.getInstance()
        val result: UserWithUID? = suspendCancellableCoroutine { continuation ->
            mAuth.createUserWithEmailAndPassword(userDomain.email, userDomain.password)
                .addOnCompleteListener { task ->
                    var userWithUID: UserWithUID? = null
                    if (task.isSuccessful) {
                        userWithUID = UserWithUID(
                            userId = mAuth.currentUser?.uid.toString(),
                            email = userDomain.email,
                            firstName = userDomain.firstName,
                            lastName = userDomain.lastName
                        )
                    }
                    continuation.resume(userWithUID) {}
                }
        }
        return result
    }

    override fun currentUser(): String? {
        mAuth = FirebaseAuth.getInstance()
        return mAuth.currentUser?.uid
    }

    override fun signOut() {
        mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
    }

    override suspend fun insertUser(userWithUID: UserWithUID) {
        val database = Firebase.database
        val myRef = database.getReference(USER_KEY)
        myRef.child(userWithUID.userId).setValue(userWithUID)
    }

    override suspend fun getUsers(): UserCloudData? {
        mAuth = FirebaseAuth.getInstance()
        val currentUserUID: String
        mAuth.currentUser?.uid.let { currentUserUID = it.toString() }

        val rootRef = FirebaseDatabase.getInstance().reference
        val messageRef = rootRef.child(USER_KEY).child(currentUserUID)

        val user: UserCloudData? = suspendCancellableCoroutine {
            val valueEventListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    it.resume(dataSnapshot.getValue(UserCloudData::class.java)){}
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("firebaseErrorUser", error.message)
                }
            }
            messageRef.addListenerForSingleValueEvent(valueEventListener)
        }
        return user
    }
}