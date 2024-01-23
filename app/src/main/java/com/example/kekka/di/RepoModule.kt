package com.example.kekka.di

import com.example.kekka.data.repository.LogInRepositoryImpl
import com.example.kekka.domain.repository.DataStoreRepository
import com.example.kekka.data.repository.DataStoreRepositoryImpl
import com.example.kekka.data.repository.RegisterRepositoryImpl
import com.example.kekka.domain.repository.LogInRepository
import com.example.kekka.domain.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindLoginRepository(loginRepository: LogInRepositoryImpl): LogInRepository

    @Binds
    abstract fun bindRegisterRepository(registerRepository: RegisterRepositoryImpl): RegisterRepository

    @Binds
    abstract fun bindDataStoreRepository(dataStoreRepository: DataStoreRepositoryImpl): DataStoreRepository

}
