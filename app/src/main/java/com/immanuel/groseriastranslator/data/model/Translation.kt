package com.immanuel.groseriastranslator.data.model

data class Translation(
    val id: Int,
    val fromWordId: Int,         // ID de Word
    val languageTo: String,      // "es", "fr"
    val translation: String,
    val translationSynonyms: List<String>
)
