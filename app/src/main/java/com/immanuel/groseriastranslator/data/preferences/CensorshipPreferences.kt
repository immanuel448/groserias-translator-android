package com.immanuel.groseriastranslator.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extensión de Context que crea un DataStore de tipo Preferences
// Se declara como singleton para que exista UNA sola instancia en toda la app
private val Context.dataStore by preferencesDataStore(
    name = "settings" // nombre del archivo donde se guardan las preferencias
)

// Clase encargada EXCLUSIVAMENTE de leer y guardar la preferencia de censura
// No contiene lógica de UI ni de negocio
class CensorshipPreferences(private val context: Context) {

    // Clave con la que se guarda el valor booleano en DataStore
    // "censorship_enabled" es el nombre físico dentro del archivo
    private val CENSORSHIP_KEY = booleanPreferencesKey("censorship_enabled")

    // Flow que emite el estado actual de la censura
    // Cada vez que cambia el valor en disco, este Flow emite el nuevo valor
    val censorshipEnabled: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            // Si no existe la clave todavía, por defecto la censura está ACTIVADA
            preferences[CENSORSHIP_KEY] ?: true
        }

    // Función suspend porque DataStore trabaja de forma asíncrona
    // Guarda el nuevo valor de la censura en disco
    suspend fun setCensorshipEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[CENSORSHIP_KEY] = enabled
        }
    }
}

