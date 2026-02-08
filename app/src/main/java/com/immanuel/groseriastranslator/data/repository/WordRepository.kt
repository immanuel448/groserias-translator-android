package com.immanuel.groseriastranslator.data.repository

import com.immanuel.groseriastranslator.data.model.Word

class WordRepository {

    fun getWords(language: String): List<Word> {
        return listOf(
            Word(
                id = 1,
                base = "fuck",
                language = "en",
                synonyms = listOf("fucking", "fucker", "motherfucker")
            )
        )
    }
}

