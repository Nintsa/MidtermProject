package com.example.kekka.domain.usecase.register

import com.example.kekka.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend operator fun invoke(email: String, password: String) =
        registerRepository.register(email = email, password = password)
}