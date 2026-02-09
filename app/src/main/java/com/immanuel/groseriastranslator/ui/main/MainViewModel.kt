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

    // Repositorios (simulan BD por ahora)
    private val wordRepository = WordRepository()
    private val translationRepository = TranslationRepository()

    // Lista de conceptos
    val words: List<Word> = wordRepository.getWords("en")

    // Word seleccionado
    private val _selectedWord = MutableStateFlow(words.first())
    val selectedWord: StateFlow<Word> = _selectedWord.asStateFlow()

    // Variante seleccionada (base o sinónimo)
    private val _selectedVariant = MutableStateFlow(words.first().variants.first())
    val selectedVariant: StateFlow<WordVariant> = _selectedVariant.asStateFlow()

    // Traducción del concepto
    private val _translation = MutableStateFlow<Translation>(
        translationRepository.getTranslation(
            fromWordId = words.first().id,
            languageTo = "es"
        )
    )
    val translation: StateFlow<Translation> = _translation.asStateFlow()

    // Cambiar palabra (concepto)
    fun selectWord(word: Word) {
        _selectedWord.value = word
        _selectedVariant.value = word.variants.first()

        _translation.value =
            translationRepository.getTranslation(word.id, "es")
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
