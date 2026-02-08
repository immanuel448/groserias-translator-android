package com.immanuel.groseriastranslator.ui.main

import androidx.lifecycle.ViewModel
import com.immanuel.groseriastranslator.data.repository.WordRepository
import com.immanuel.groseriastranslator.data.repository.TranslationRepository

class MainViewModel : ViewModel() {

    // Repositorios viven aquí, NO en la Activity
    private val wordRepository = WordRepository()
    private val translationRepository = TranslationRepository()

    // Datos expuestos (por ahora simples, luego serán State)
    val word = wordRepository.getWords("en").first()

    val translation = translationRepository
        .getTranslations(word.id, "es")
        .first()
}
