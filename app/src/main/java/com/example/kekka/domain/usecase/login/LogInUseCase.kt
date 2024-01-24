package com.example.kekka.domain.usecase.login

import com.example.kekka.domain.repository.LogInRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val logInRepository: LogInRepository) {
    suspend operator fun invoke(email: String, password: String) =
        logInRepository.logIn(email = email, password = password)
}