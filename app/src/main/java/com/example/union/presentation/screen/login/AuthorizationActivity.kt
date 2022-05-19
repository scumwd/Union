package com.example.union.presentation.screen.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.data.repository.UserRepositoryImpl
import com.example.data.storage.user.UserStorageImpl
import com.example.domain.auth.AuthorizationUseCase
import com.example.domain.models.User
import com.example.domain.save.SaveUserUseCase
import com.example.union.R


class AuthorizationActivity : AppCompatActivity() {

    private val userStorage by lazy { UserStorageImpl() }
    private val userRepository by lazy { UserRepositoryImpl(userStorage) }
    private val authorization = AuthorizationUseCase(userRepository = userRepository)
    private val saveUser = SaveUserUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authorization)

        val signUp: Button = findViewById(R.id.signUp)

        val email: EditText = findViewById(R.id.edEmailAddress)
        val firstName: EditText = findViewById(R.id.edFirstName)
        val lastName: EditText = findViewById(R.id.edLastName)
        val password: EditText = findViewById(R.id.edPassword)

        signUp.setOnClickListener{
            if (!(email.text.toString().isEmpty() && firstName.text.toString().isEmpty() && lastName.text.toString().isEmpty() && password.text.toString().isEmpty()))
            {
                val user = User(
                    email = email.text.toString(),
                    firstName = firstName.text.toString(),
                    lastName = lastName.text.toString(),
                    password = password.text.toString()
                )
                authorization.execute(user,this)

            }
            else
                Toast.makeText(applicationContext, "Пожалуйста, заполните все поля.", Toast.LENGTH_LONG).show()
        }

    }
}