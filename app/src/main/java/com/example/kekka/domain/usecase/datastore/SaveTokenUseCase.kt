package com.example.kekka.domain.usecase.datastore

import com.example.kekka.domain.repository.DataStoreRepository
import com.example.kekka.domain.user_data_key.PreferenceKeys
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke(token: String) {
        dataStoreRepository.saveString(key = PreferenceKeys.TOKEN, value = token)
    }
}