package com.example.kekka

import com.squareup.moshi.Json

data class Questions(
    @Json (name = "response_code")
    val responseCode: Int,
    val results: List<Result>
)