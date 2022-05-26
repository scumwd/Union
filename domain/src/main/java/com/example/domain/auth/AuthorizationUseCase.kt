package com.example.domain.auth

import com.example.domain.models.UserDomain
import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

class AuthorizationUseCase(private val userRepository: UserRepository) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var saveUserUseCase: SaveUserUseCase


    @ExperimentalCoroutinesApi
    suspend fun execute(userDomain: UserDomain): Boolean {
        mAuth = FirebaseAuth.getInstance()
        saveUserUseCase = SaveUserUseCase()
        val result: Boolean = suspendCancellableCoroutine { continuation ->
            mAuth.createUserWithEmailAndPassword(userDomain.email, userDomain.password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        val userWithUID = UserWithUID(
                            userId = mAuth.currentUser?.uid.toString(),
                            email = userDomain.email,
                            firstName = userDomain.firstName,
                            lastName = userDomain.lastName,
                            password = userDomain.password
                        )
                        saveUserUseCase.insert(
                            userRepository = userRepository,
                            userWithUID = userWithUID
                        ) {}
                    }
                    continuation.resume(task.isSuccessful) {}
                }
        }
        return result
    }
}