package com.example.kekka.state.log_in

data class LogInState (
    val isLoading: Boolean = false,
    val accessToken: String? = null,
    val errorMessage: String? = null
)