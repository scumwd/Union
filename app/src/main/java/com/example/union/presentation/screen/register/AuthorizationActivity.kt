package com.example.union.presentation.screen.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.repository.UserRepositoryImpl
import com.example.data.storage.user.UserStorageImpl
import com.example.domain.auth.AuthorizationUseCase
import com.example.domain.models.UserDomain
import com.example.domain.models.UserWithUID
import com.example.domain.save.SaveUserUseCase
import com.example.union.R
import com.example.union.presentation.USER_REPOSITORY
import com.example.union.presentation.screen.home.HomeViewModel
import com.example.union.presentation.screen.login.AuthenticationActivity
import com.google.firebase.auth.FirebaseAuth


class AuthorizationActivity : AppCompatActivity() {

    lateinit var authorizationUseCase: AuthorizationUseCase
    lateinit var viewModel: AuthorizationViewModel
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authorization)
        viewModel = ViewModelProvider(this).get(AuthorizationViewModel::class.java)
        init()
    }

    private fun init(){
        val signUp: Button = findViewById(R.id.signUp)
        val logIn: Button = findViewById(R.id.logIn)
        val email: EditText = findViewById(R.id.edEmailAddress)
        val firstName: EditText = findViewById(R.id.edFirstName)
        val lastName: EditText = findViewById(R.id.edLastName)
        val password: EditText = findViewById(R.id.edPassword)

        signUp.setOnClickListener {
            if (!(email.text.toString().isEmpty() && firstName.text.toString()
                    .isEmpty() && lastName.text.toString().isEmpty() && password.text.toString()
                    .isEmpty())
            ) {
                viewModel.addUser(getUser())
                Toast.makeText(
                    applicationContext,
                    "Вы были успешно зарегистрированы.",
                    Toast.LENGTH_LONG
                ).show()

            } else
                Toast.makeText(
                    applicationContext,
                    "Пожалуйста, заполните все поля.",
                    Toast.LENGTH_LONG
                ).show()
        }

        logIn.setOnClickListener {
            val intent = Intent(this@AuthorizationActivity, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun getUser(): UserDomain{
        val email: EditText = findViewById(R.id.edEmailAddress)
        val firstName: EditText = findViewById(R.id.edFirstName)
        val lastName: EditText = findViewById(R.id.edLastName)
        val password: EditText = findViewById(R.id.edPassword)
        val user = UserDomain(
            email = email.text.toString(),
            firstName = firstName.text.toString(),
            lastName = lastName.text.toString(),
            password = password.text.toString()
        )
        return user
    }
}