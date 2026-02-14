package com.immanuel.groseriastranslator.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immanuel.groseriastranslator.data.preferences.CensorshipPreferences
import com.immanuel.groseriastranslator.data.repository.WordRepository
import com.immanuel.groseriastranslator.data.repository.TranslationRepository
import com.immanuel.groseriastranslator.data.model.word.Word
import com.immanuel.groseriastranslator.data.model.word.WordVariant
import com.immanuel.groseriastranslator.data.model.translation.Translation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val preferences: CensorshipPreferences
) : ViewModel() {

    private val wordRepository = WordRepository()
    private val translationRepository = TranslationRepository()

    // Lista de conceptos
    val words: List<Word> = wordRepository.getWords("en")

    // Variante seleccionada (unidad principal)
    private val _selectedVariant =
        MutableStateFlow(words.first().variants.first())
    val selectedVariant: StateFlow<WordVariant> =
        _selectedVariant.asStateFlow()

    // Concepto derivado de la variante seleccionada
    private val _selectedWord =
        MutableStateFlow(words.first())
    val selectedWord: StateFlow<Word> =
        _selectedWord.asStateFlow()

    // Traducción
    private val _translation = MutableStateFlow(
        translationRepository.getTranslation(
            fromWordId = words.first().id,
            languageTo = "es"
        )
    )
    val translation: StateFlow<Translation> =
        _translation.asStateFlow()

    // Cambiar concepto
    fun selectWord(word: Word) {
        _selectedWord.value = word
        _selectedVariant.value = word.variants.first()

        _translation.value =
            translationRepository.getTranslation(word.id, "es")
    }

    // Cambiar variante explícitamente
    fun selectVariant(variant: WordVariant) {
        _selectedVariant.value = variant

        val parentWord = words.first { it.variants.contains(variant) }
        _selectedWord.value = parentWord

        _translation.value =
            translationRepository.getTranslation(parentWord.id, "es")
    }

    // --- CENSURA ---

    private val _isCensored = MutableStateFlow(true)
    val isCensored: StateFlow<Boolean> = _isCensored.asStateFlow()

    init {
        viewModelScope.launch {
            preferences.censorshipEnabled.collect { enabled ->
                _isCensored.value = enabled
            }
        }
    }

    fun toggleCensorship() {
        viewModelScope.launch {
            preferences.setCensorshipEnabled(!_isCensored.value)
        }
    }
}
