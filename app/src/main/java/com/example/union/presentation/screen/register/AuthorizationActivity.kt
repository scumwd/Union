package com.example.union.presentation.screen.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.models.UserDomain
import com.example.union.R
import com.example.union.app.App
import com.example.union.databinding.AuthorizationBinding
import com.example.union.presentation.ViewModelFactory
import com.example.union.presentation.screen.login.AuthenticationActivity
import kotlinx.android.synthetic.main.authorization.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject


class AuthorizationActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    lateinit var binding: AuthorizationBinding

    private val viewModel: AuthorizationViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthorizationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        (applicationContext as App).appComponent.inject(this)

        init()
    }

    @ExperimentalCoroutinesApi
    private fun init() {

        binding.run {
            signUp.setOnClickListener {
                signUp()
            }

            logIn.setOnClickListener {
                logIn()
            }
        }
    }

    private fun logIn() {
        val intent = Intent(this@AuthorizationActivity, AuthenticationActivity::class.java)
        startActivity(intent)
        finish()
    }

    @ExperimentalCoroutinesApi
    private fun signUp() {
        binding.run {
            if (!(edEmailAddress.text.toString().isEmpty() && edFirstName.text.toString()
                    .isEmpty() && edLastName.text.toString()
                    .isEmpty() && edPassword.text.toString()
                    .isEmpty())
            ) lifecycleScope.launch {
                if (viewModel.addUser(getUser())) {
                    Toast.makeText(
                        applicationContext,
                        "???? ???????? ?????????????? ????????????????????????????????.",
                        Toast.LENGTH_LONG
                    ).show()
                } else
                    if (EMAIL_ADDRESS.matcher(edEmailAddress.text.toString()).matches())
                        Toast.makeText(
                            applicationContext,
                            "???????????????????????? ?? ?????????? ???????????? ?????? ??????????????????????????????.",
                            Toast.LENGTH_LONG
                        ).show()
                    else
                        Toast.makeText(
                            applicationContext,
                            "?????????????????? ?????????? ??????????????????????.",
                            Toast.LENGTH_LONG
                        ).show()
            } else
                Toast.makeText(
                    applicationContext,
                    "????????????????????, ?????????????????? ?????? ????????.",
                    Toast.LENGTH_LONG
                ).show()
        }

    }

    private fun getUser(): UserDomain {
        val email: EditText = findViewById(R.id.edEmailAddress)
        val firstName: EditText = findViewById(R.id.edFirstName)
        val lastName: EditText = findViewById(R.id.edLastName)
        val password: EditText = findViewById(R.id.edPassword)
        return UserDomain(
            email = email.text.toString(),
            firstName = firstName.text.toString(),
            lastName = lastName.text.toString(),
            password = password.text.toString()
        )
    }
}