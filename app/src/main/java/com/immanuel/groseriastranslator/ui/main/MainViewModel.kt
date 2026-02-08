package com.immanuel.groseriastranslator.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.immanuel.groseriastranslator.data.repository.WordRepository
import com.immanuel.groseriastranslator.data.repository.TranslationRepository


    class MainViewModel : ViewModel() {
    private val wordRepository = WordRepository()
    private val translationRepository = TranslationRepository()

    val word = wordRepository.getWords("en").first()
    val translation = translationRepository
        .getTranslations(word.id, "es")
        .first()

    // 🔹 Estado de censura (UI)
    var isCensored = mutableStateOf(true)
        private set

    fun toggleCensorship() {
        isCensored.value = !isCensored.value
    }
}
