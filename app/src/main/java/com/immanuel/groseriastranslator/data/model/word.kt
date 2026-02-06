package com.immanuel.groseriastranslator.data.model

data class Word(
    val id: Int,
    val base: String,
    val language: String,        // "en", "es", "pt"
    val synonyms: List<String>,  // mismo idioma
    val censored: String
)

