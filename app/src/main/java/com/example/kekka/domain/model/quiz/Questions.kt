package com.example.kekka.domain.model.quiz

import com.squareup.moshi.Json

data class Questions(
    @Json(name = "response_code")
    val responseCode: Int,
    val results: List<Result>
)
