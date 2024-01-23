package com.example.kekka.data.repository

import com.example.kekka.data.common.Resource
import com.example.kekka.domain.model.login.GetToken
import com.example.kekka.domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(

) : RegisterRepository {
    override suspend fun register(
        name: String,
        username: String,
        email: String,
        password: String
    ): Flow<Resource<GetToken>> {
        TODO("Not yet implemented")
    }
}
