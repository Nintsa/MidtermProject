package com.example.kekka.domain.usecase.validator

class PasswordValidatorUseCase {
    operator fun invoke(password: String): Boolean = password.isNotBlank()
}