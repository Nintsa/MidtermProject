package com.example.kekka.domain.model.login

data class GetToken(
    val needsMfa: Boolean,
    val accessToken: String,
    val refreshToken: String,
)