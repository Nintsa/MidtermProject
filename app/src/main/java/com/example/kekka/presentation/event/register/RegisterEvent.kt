package com.example.kekka.presentation.event.register

sealed interface RegisterEvent {

    data object Registered : RegisterEvent

    data object ResetErrorMessage : RegisterEvent
}
