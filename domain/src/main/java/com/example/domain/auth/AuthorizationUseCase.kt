package com.example.domain.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.domain.models.User
import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine

class AuthorizationUseCase(private val userRepository: UserRepository) {

    private lateinit var mAuth: FirebaseAuth


    fun execute(user: User, context: Context) {
        mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userWithUID: UserWithUID = UserWithUID(
                        userId = mAuth.currentUser?.uid.toString(),
                        email = user.email,
                        firstName = user.firstName,
                        lastName = user.lastName,
                        password = user.password)
                    Toast.makeText(context, "Вы были успешно зарегистрированы.", Toast.LENGTH_LONG)
                        .show()
                    userRepository.saveUser(userWithUID)
                }
                else
                    Toast.makeText(
                        context,
                        "Пользователь с такой почтой уже зарегистрирован.",
                        Toast.LENGTH_LONG
                    ).show()
            }
    }
    //fun extcute1 () = suspendCancellableCoroutine { continuation ->

   // }

}