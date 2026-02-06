package com.immanuel.groseriastranslator.data.repository

import com.immanuel.groseriastranslator.data.model.Translation

class TranslationRepository {

    fun getTranslations(fromWordId: Int, languageTo: String): List<Translation> {
        return listOf(
            Translation(
                id = 1,
                fromWordId = fromWordId,
                languageTo = "es",
                translation = "joder",
                translationSynonyms = listOf("chingar", "cabrón")
            )
        )
    }
}
