package com.example.kekka.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kekka.domain.usecase.datastore.SaveTokenUseCase
import com.example.kekka.domain.usecase.register.RegisterUseCase
import com.example.kekka.domain.usecase.validator.EmailValidatorUseCase
import com.example.kekka.domain.usecase.validator.PasswordValidatorUseCase
import com.example.kekka.presentation.event.register.RegisterEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val emailValidator: EmailValidatorUseCase,
    private val passwordValidator: PasswordValidatorUseCase
) : ViewModel() {


    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.Register -> validateForm(
                name = event.name,
                username = event.username,
                email = event.email,
                password = event.password,
            )

            is RegisterEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }

    private fun validateForm(name: String, username: String, email: String, password: String) {
        val isEmailValid = emailValidator(email)
        val isPasswordValid = passwordValidator(password)

        val areFieldsValid =
            listOf(isEmailValid, isPasswordValid)
                .all { it }

        if (!areFieldsValid) {
            updateErrorMessage(message = "Fields are not valid!")
            return
        }

        register(
            name = name,
            username = username,
            email = email,
            password = password
        )
    }

    private fun register(name: String, username: String, email: String, password: String) {
        viewModelScope.launch {
            registerUseCase(
                name = name,
                username = username,
                email = email,
                password = password
            )
        }
    }

    private fun updateErrorMessage(message: String?) {
    }
}