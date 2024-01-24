package com.example.kekka.di

import com.example.kekka.di.Util.BASE
import com.example.kekka.data.common.HandleResponse
import com.example.kekka.data.service.LogInService
import com.example.kekka.data.service.QuizService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }


    @Provides
    fun provideRetrofitInstance(moshi: Moshi): Retrofit = Retrofit.Builder()
        .client(OkHttpClient().newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build())
        .baseUrl(BASE)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideLoginService(retrofit: Retrofit): LogInService =
        retrofit.create(LogInService::class.java)

    @Provides
    fun providesQuizService(retrofit: Retrofit): QuizService =
        retrofit.create(QuizService::class.java)

    @Provides
    fun provideNetworkErrorHandler(): HandleResponse = HandleResponse()

}
