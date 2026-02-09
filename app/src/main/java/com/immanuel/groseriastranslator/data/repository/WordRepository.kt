package com.immanuel.groseriastranslator.data.repository

import com.immanuel.groseriastranslator.data.model.word.Word
import com.immanuel.groseriastranslator.data.model.word.WordVariant
import com.immanuel.groseriastranslator.data.model.common.PartOfSpeech

class WordRepository {

    fun getWords(language: String): List<Word> {
        return listOf(

            Word(
                id = 1,
                language = "en",
                meaning = "Expresión vulgar usada para enfatizar enojo, frustración o sorpresa.",
                intensity = 5,
                notes = "Muy ofensiva en la mayoría de contextos.",
                variants = listOf(
                    WordVariant(
                        id = 1,
                        text = "fuck",
                        partOfSpeech = PartOfSpeech.VERB,
                        intensity = 5,
                        examples = listOf("Fuck this!", "I don't give a fuck.")
                    ),
                    WordVariant(
                        id = 2,
                        text = "fucking",
                        partOfSpeech = PartOfSpeech.ADJECTIVE,
                        intensity = 5,
                        examples = listOf("This is fucking insane.")
                    ),
                    WordVariant(
                        id = 3,
                        text = "motherfucker",
                        partOfSpeech = PartOfSpeech.NOUN,
                        intensity = 5,
                        examples = listOf("That guy is a motherfucker.")
                    )
                )
            ),

            Word(
                id = 2,
                language = "en",
                meaning = "Palabra vulgar relacionada con excremento, usada como insulto o queja.",
                intensity = 4,
                notes = "Común en lenguaje informal.",
                variants = listOf(
                    WordVariant(
                        id = 4,
                        text = "shit",
                        partOfSpeech = PartOfSpeech.NOUN,
                        intensity = 4,
                        examples = listOf("This is shit.")
                    ),
                    WordVariant(
                        id = 5,
                        text = "bullshit",
                        partOfSpeech = PartOfSpeech.NOUN,
                        intensity = 4,
                        examples = listOf("That's complete bullshit.")
                    )
                )
            )
        )
    }
}
