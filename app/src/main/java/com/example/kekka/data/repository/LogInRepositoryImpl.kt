package com.example.kekka.data.repository

import com.example.kekka.data.common.HandleResponse
import com.example.kekka.data.common.Resource
import com.example.kekka.data.mapper.asResource
import com.example.kekka.data.mapper.toDomain
import com.example.kekka.data.service.LogInService
import com.example.kekka.domain.model.login.GetToken
import com.example.kekka.domain.repository.LogInRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(
    private val logInService: LogInService,
    private val handleResponse: HandleResponse,
) : LogInRepository {

    override suspend fun logIn(email: String, password: String): Flow<Resource<GetToken>> {
        return handleResponse.safeApiCall {
            logInService.logIn(email = email, password = password)
        }.asResource {
            it.toDomain()
        }
    }
}
