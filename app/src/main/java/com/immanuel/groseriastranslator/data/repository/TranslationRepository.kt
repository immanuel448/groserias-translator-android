package com.immanuel.groseriastranslator.data.repository

import com.immanuel.groseriastranslator.data.model.translation.Translation
import com.immanuel.groseriastranslator.data.model.translation.TranslationVariant
import com.immanuel.groseriastranslator.data.model.common.PartOfSpeech

class TranslationRepository {

    fun getTranslation(
        fromWordId: Int,
        languageTo: String
    ): Translation {
        return when (fromWordId) {

            // fuck
            1 -> Translation(
                id = 1,
                fromWordId = 1,
                languageTo = languageTo,
                meaning = "Expresión vulgar usada para enfatizar enojo, frustración o intensidad.",
                variants = listOf(
                    TranslationVariant(
                        text = "joder",
                        partOfSpeech = PartOfSpeech.VERB,
                        intensity = 4,
                        examples = listOf("Me jodiste el día")
                    ),
                    TranslationVariant(
                        text = "chingar",
                        partOfSpeech = PartOfSpeech.VERB,
                        intensity = 5
                    ),
                    TranslationVariant(
                        text = "cabrón",
                        partOfSpeech = PartOfSpeech.NOUN,
                        intensity = 4
                    )
                )
            )

            // shit
            2 -> Translation(
                id = 2,
                fromWordId = 2,
                languageTo = languageTo,
                meaning = "Se usa para referirse a algo de mala calidad o a una situación negativa.",
                variants = listOf(
                    TranslationVariant(
                        text = "mierda",
                        partOfSpeech = PartOfSpeech.NOUN,
                        intensity = 3
                    ),
                    TranslationVariant(
                        text = "porquería",
                        partOfSpeech = PartOfSpeech.NOUN,
                        intensity = 2
                    )
                )
            )

            else -> Translation(
                id = 0,
                fromWordId = fromWordId,
                languageTo = languageTo,
                meaning = "Sin traducción disponible.",
                variants = emptyList()
            )
        }
    }
}
