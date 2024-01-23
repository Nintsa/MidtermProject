package com.example.kekka.data.mapper

import com.example.kekka.data.model.LogInDto
import com.example.kekka.domain.model.login.GetToken

fun LogInDto.toDomain() =
    GetToken(
        needsMfa = needsMfa ?: false,
        accessToken = accessToken ?: "",
        refreshToken = refreshToken ?: ""
    )