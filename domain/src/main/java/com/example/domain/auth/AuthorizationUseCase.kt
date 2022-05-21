package com.example.domain.auth

import android.content.Context
import android.widget.Toast
import com.example.domain.models.UserDomain
import com.example.domain.models.UserWithUID
import com.example.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class AuthorizationUseCase(private val userRepository: UserRepository) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var saveUserUseCase: SaveUserUseCase


    fun execute(userDomain: UserDomain, context: Context) {
        mAuth = FirebaseAuth.getInstance()
        saveUserUseCase = SaveUserUseCase()
        mAuth.createUserWithEmailAndPassword(userDomain.email, userDomain.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userWithUID: UserWithUID = UserWithUID(
                        userId = mAuth.currentUser?.uid.toString(),
                        email = userDomain.email,
                        firstName = userDomain.firstName,
                        lastName = userDomain.lastName,
                        password = userDomain.password
                    )
                    Toast.makeText(context, "Вы были успешно зарегистрированы.", Toast.LENGTH_LONG)
                        .show()
                    saveUserUseCase.insert(userRepository = userRepository, userWithUID = userWithUID){}
                } else
                    Toast.makeText(
                        context,
                        "Пользователь с такой почтой уже зарегистрирован.",
                        Toast.LENGTH_LONG
                    ).show()
            }
    }
}