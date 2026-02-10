package com.immanuel.groseriastranslator.domain.censor

/**
 * Censura un texto según la configuración.
 * - Si está desactivado (enabled=false) devuelve el texto original.
 * - Si una palabra es corta, se reemplaza completamente por asteriscos.
 * - Si es normal, se ocultan todos los caracteres excepto el primero y el último.
 * - Si es larga (más de longWordThreshold caracteres), se muestran los 2 primeros y 2 últimos caracteres.
 *
 * Maneja múltiples palabras separadas por espacios.
 */
fun censor(text: String, enabled: Boolean, longWordThreshold: Int = 6): String {
    if (!enabled) return text

    // Separar por espacios y comas
    return text.split(" ", ",").joinToString(" ") { word ->
        if (word.length <= 2) "*".repeat(word.length)
        else if (word.length > longWordThreshold)
            word.take(2) + "*".repeat(word.length - 4) + word.takeLast(2)
        else
            word.first() + "*".repeat(word.length - 2) + word.last()
    }
}

