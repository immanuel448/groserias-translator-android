package com.immanuel.groseriastranslator.data.model.word

import com.immanuel.groseriastranslator.data.model.common.PartOfSpeech

/**
 * Representa una FORMA CONCRETA de la palabra
 * Ej: "fuck", "fucking", "motherfucker"
 */
data class WordVariant(
    val id: Int,

    // Texto literal de la palabra
    val text: String,

    // Función gramatical: verbo, sustantivo, adjetivo, etc.
    val partOfSpeech: PartOfSpeech,

    // Nivel de agresividad específico de esta variante
    val intensity: Int,

    // Ejemplos de uso (opcionales)
    val examples: List<String> = emptyList()
)
