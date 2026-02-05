package com.Immanuel.groseriastranslator.data.model

data class Word(
    val id: Int,
    val original: String,
    val languageFrom: String,
    val languageTo: String,
    val translation: String,
    val censored: String
)