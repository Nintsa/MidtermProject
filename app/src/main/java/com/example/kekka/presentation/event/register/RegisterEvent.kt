package com.example.kekka.presentation.event.register

open class RegisterEvent {
        data class Register(val name: String, val username: String, val email: String, val password: String) : RegisterEvent()
        object ResetErrorMessage : RegisterEvent()
}