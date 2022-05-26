package com.example.union.presentation.screen.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.domain.auth.AuthenticationUseCase
import com.example.union.databinding.AuthenticationBinding
import com.example.union.presentation.MainActivity
import com.example.union.presentation.screen.register.AuthorizationActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: AuthenticationBinding
    lateinit var viewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthenticationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]

        init()
    }

    private fun init() {
        if (viewModel.checkCurrentUser()) {
            val intent = Intent(this@AuthenticationActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            binding.run {
                signUp.setOnClickListener {
                    signUp()
                }

                logIn.setOnClickListener {
                    logIn()
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    private fun logIn() {
        binding.run {
            if (edEmailAddress.text.toString().isEmpty() || edPassword.text.toString()
                    .isEmpty()
            )
                Toast.makeText(
                    this@AuthenticationActivity,
                    "Пожалуйста, заполните все поля.",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                lifecycleScope.launch {
                    if (viewModel.logIn(
                            edEmailAddress.text.toString(),
                            edPassword.text.toString()
                        )
                    ) {
                        val intent = Intent(this@AuthenticationActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@AuthenticationActivity,
                            "Неверное имя пользователя или пароль.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun signUp() {
        val intent = Intent(this@AuthenticationActivity, AuthorizationActivity::class.java)
        startActivity(intent)
        finish()
    }
}
