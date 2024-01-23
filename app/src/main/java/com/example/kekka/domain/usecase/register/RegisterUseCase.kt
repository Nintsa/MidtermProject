package com.example.kekka.domain.usecase.register

import com.example.kekka.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend operator fun invoke(name: String, username: String, email: String, password: String) =
        registerRepository.register(
            name = name,
            username = username,
            email = email,
            password = password
        )
}
