package com.immanuel.groseriastranslator.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immanuel.groseriastranslator.data.preferences.CensorshipPreferences
import com.immanuel.groseriastranslator.data.repository.WordRepository
import com.immanuel.groseriastranslator.data.repository.TranslationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    // Preferencias que leen y guardan la censura en disco
    private val preferences: CensorshipPreferences
) : ViewModel() {

    // Repositorios: fuente de datos (simulada por ahora)
    private val wordRepository = WordRepository()
    private val translationRepository = TranslationRepository()

    // Datos base que la UI va a mostrar
    val word = wordRepository.getWords("en").first()
    val translation = translationRepository
        .getTranslations(word.id, "es")
        .first()

    // Estado observable para Compose
    // La UI se redibuja cuando este valor cambia
    private val _isCensored = MutableStateFlow(true)
    val isCensored: StateFlow<Boolean> = _isCensored.asStateFlow()

    init {
        // Al iniciar el ViewModel:
        // escuchamos cambios en DataStore
        // y actualizamos el estado en memoria
        viewModelScope.launch {
            preferences.censorshipEnabled.collect { enabled ->
                _isCensored.value = enabled
            }
        }
    }

    // Se llama cuando el usuario mueve el Switch
    fun toggleCensorship() {
        viewModelScope.launch {
            // Calculamos el nuevo valor
            val newValue = !_isCensored.value

            // Guardamos el cambio en DataStore
            // (esto también disparará el collect de arriba)
            preferences.setCensorshipEnabled(newValue)
        }
    }
}
