package com.example.kekka.domain.repository

import com.example.kekka.data.common.Resource
import com.example.kekka.domain.model.login.GetToken
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun register(name: String, username: String, email: String, password: String): Flow<Resource<GetToken>>
}