package com.Immanuel.groseriastranslator.data.repository

import com.Immanuel.groseriastranslator.data.model.Word

class WordRepository {

    fun getWords(): List<Word> {
        return listOf(
            Word(
                id = 1,
                original = "fuck",
                languageFrom = "en",
                languageTo = "es",
                translation = "joder",
                censored = "f***"
            )
        )
    }
}
