package com.example.kekka.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.kekka.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {

    override suspend fun saveString(key: Preferences.Key<String>, value: String) {
        dataStore.edit { settings ->
            settings[key] = value
        }
    }

    override suspend fun readString(key: Preferences.Key<String>): Flow<String> = dataStore.data
        .map { preferences ->
            preferences[key] ?: ""
        }

    override suspend fun clear() {
        dataStore.edit {
            it.clear()
        }
    }
}
