package com.example.kekka.data.repository

import androidx.datastore.preferences.core.Preferences
import com.example.kekka.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(

) : DataStoreRepository {
    override suspend fun saveString(key: Preferences.Key<String>, value: String) {
        TODO("Not yet implemented")
    }

    override fun readString(key: Preferences.Key<String>): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }
}
