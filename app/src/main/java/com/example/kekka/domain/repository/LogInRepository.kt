package com.example.kekka.domain.repository

import com.example.kekka.data.common.Resource
import com.example.kekka.domain.model.login.GetToken
import kotlinx.coroutines.flow.Flow

interface LogInRepository {
    suspend fun logIn(email: String, password: String): Flow<Resource<GetToken>>
}