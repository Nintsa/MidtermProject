package com.example.kekka

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET() //aq ar vici jer ra nawili unda chavwero
    suspend fun getQuestions():Response <Questions>
}