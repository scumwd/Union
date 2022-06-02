package com.example.union.presentation.screen.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.union.app.App
import com.example.union.databinding.AuthenticationBinding
import com.example.union.presentation.MainActivity
import com.example.union.presentation.ViewModelFactory
import com.example.union.presentation.screen.register.AuthorizationActivity
import kotlinx.coroutines.*
import javax.inject.Inject

class AuthenticationActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: AuthenticationViewModel by viewModels {
        factory
    }

    private lateinit var binding: AuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthenticationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        (applicationContext as App).appComponent.inject(this)

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
                        withContext(lifecycleScope.coroutineContext) {
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
    }

    private fun signUp() {
        val intent = Intent(this@AuthenticationActivity, AuthorizationActivity::class.java)
        startActivity(intent)
        finish()
    }
}
