package com.example.kekka

import retrofit2.Retrofit

object RetrofitInstance { //es rashi mchirdeba ??????
    val api: ApiInterface by lazy {
        Retrofit.Builder().baseUrl(Util.BASE)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build().create(ApiInterface::class.java)
    }
}

