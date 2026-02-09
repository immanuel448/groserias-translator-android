package com.immanuel.groseriastranslator.data.repository

import com.immanuel.groseriastranslator.data.model.word.Word

class WordRepository {

    fun getWords(language: String): List<Word> {
        return listOf(
            Word(
                id = 1,
                base = "fuck",
                language = "en",
                synonyms = listOf("fucking", "fucker", "motherfucker")
            ),
            Word(
                id = 2,
                base = "shit",
                language = "en",
                synonyms = listOf("bullshit", "shithead")
            ),
            Word(
                id = 3,
                base = "asshole",
                language = "en",
                synonyms = listOf("ass", "jerk")
            )
        )
    }
}

