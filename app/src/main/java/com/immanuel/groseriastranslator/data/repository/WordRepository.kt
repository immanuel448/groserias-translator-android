package com.immanuel.groseriastranslator.data.repository

import com.immanuel.groseriastranslator.data.model.Word

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
