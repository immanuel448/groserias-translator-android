package com.immanuel.groseriastranslator.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore singleton
private val Context.dataStore by preferencesDataStore(name = "settings")

class CensorshipPreferences(private val context: Context) {

    private val CENSORSHIP_KEY = booleanPreferencesKey("censorship_enabled")

    val censorshipEnabled: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[CENSORSHIP_KEY] ?: true
        }

    suspend fun setCensorshipEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[CENSORSHIP_KEY] = enabled
        }
    }
}
