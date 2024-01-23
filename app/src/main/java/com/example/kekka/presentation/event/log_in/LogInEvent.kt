package com.example.kekka.presentation.event.log_in

open class LogInEvent {
    data class LogIn(val email: String, val password: String) : LogInEvent()
    object ResetErrorMessage : LogInEvent()
}