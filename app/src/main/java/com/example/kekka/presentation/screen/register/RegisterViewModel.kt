package com.example.kekka.presentation.screen.register

import androidx.lifecycle.ViewModel
import com.example.kekka.domain.usecase.LogInUseCase
import com.example.kekka.domain.usecase.datastore.SaveTokenUseCase
import com.example.kekka.domain.usecase.register.RegisterUseCase
import com.example.kekka.domain.usecase.validator.EmailValidatorUseCase
import com.example.kekka.domain.usecase.validator.PasswordValidatorUseCase
import com.example.kekka.presentation.event.log_in.LogInEvent
import com.example.kekka.presentation.event.register.RegisterEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val logInUseCase: RegisterUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val emailValidator: EmailValidatorUseCase,
    private val passwordValidator: PasswordValidatorUseCase
) : ViewModel() {


    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.Register -> validateForm(email = event.email, password = event.password)
            is RegisterEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }

    private fun validateForm(email: String, password: String) {
        val isEmailValid = emailValidator(email)
        val isPasswordValid = passwordValidator(password)

        val areFieldsValid =
            listOf(isEmailValid, isPasswordValid)
                .all { it }

        if (!areFieldsValid) {
            updateErrorMessage(message = "Fields are not valid!")
            return
        }

        logIn(email = email, password = password)
    }
}