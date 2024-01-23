package com.example.kekka.di

import com.example.kekka.domain.usecase.validator.EmailValidatorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideEmailValidator() = EmailValidatorUseCase()
}
