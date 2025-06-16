package com.example.firstapplication.viewmodel

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")
    var passwordVisible by mutableStateOf(false)
    var loginSuccess by mutableStateOf(false)

    val isFromValid: Boolean
        get() = email.isNotBlank() &&
                password.isNotBlank()

    fun onLoginClicked() {
        emailError = ""
        passwordError = ""
        loginSuccess = false

        if(email.isBlank()) {
            emailError = "Email cannot be empty"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Invalid email format"
        }

        if (password.isBlank()) {
            passwordError = "Password cannot be empty"
        }

        if (emailError.isEmpty() && passwordError.isEmpty()) {
            println("Login success with $email and $password")

            loginSuccess = true

            email = ""
            password = ""
        }
    }

}
