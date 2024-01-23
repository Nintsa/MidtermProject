package com.example.challenge.domain.usecase.datastore


import com.example.kekka.domain.repository.DataStoreRepository
import com.example.kekka.domain.user_data_key.PreferenceKeys
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke() = dataStoreRepository.readString(key = PreferenceKeys.TOKEN)
}