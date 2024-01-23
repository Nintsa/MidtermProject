package com.example.kekka.di

import com.example.kekka.Util.BASE
import com.example.kekka.data.common.HandleResponse
import com.example.kekka.data.service.LogInService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun provideLoginService(retrofit: Retrofit): LogInService =
        retrofit.create(LogInService::class.java)


    @Provides
    fun provideNetworkErrorHandler(): HandleResponse = HandleResponse()

}
