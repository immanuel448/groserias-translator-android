package com.immanuel.groseriastranslator.data.model.word

/**
 * Representa un CONCEPTO de grosería.
 * No es una palabra específica, sino la idea general.
 */
data class Word(
    val id: Int,

    // Idioma del concepto ("en", "es", etc.)
    val language: String,

    // Explicación educada del significado
    val meaning: String,

    // Nivel general de intensidad (1 = suave, 5 = muy fuerte)
    val intensity: Int,

    // Contexto cultural o notas de uso
    val notes: String? = null,

    // Variantes reales de la palabra (base + sinónimos)
    val variants: List<WordVariant>
)
