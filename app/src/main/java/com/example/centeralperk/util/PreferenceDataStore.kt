package com.example.centeralperk.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceDataStore(val context: Context) {

    /** Instance of preference dataStore */
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(AppConstant.PREFERENCE_DATASTORE)

    /**
     * Storing data in preference dataStore
     *  @param key
     *  @param data
     */
    suspend fun write(key: String, data: String?) {

        val preferenceKey = stringPreferencesKey(key)

        /** Editing dataStore to store the data with respective key*/
        context.dataStore.edit { preference ->
            data?.let { preferenceData ->
                preference[preferenceKey] = preferenceData
            }
        }
    }

    /**
     * Reading the data in preference dataStore
     * @param key
     * @return Flow of String type
     */
    suspend fun read(key: String): Flow<String?> {

        val preferenceKey = stringPreferencesKey(key)

        /** Returning the data of the key */
        return context.dataStore.data.map { preference ->
            preference[preferenceKey]
        }
    }
}