package com.immanuel.groseriastranslator.domain.censor

/**
 * Aplica censura a un texto según su longitud.
 *
 * @param text Texto original a censurar
 * @param enabled Indica si la censura está activa o no
 * @param longWordThreshold Longitud a partir de la cual se considera "palabra larga"
 */
fun censor(
    text: String,
    enabled: Boolean,
    longWordThreshold: Int = 6
): String {

    // Si la censura está desactivada, regresamos el texto tal cual
    if (!enabled) return text

    val length = text.length

    // Palabras muy cortas: se reemplazan completamente por asteriscos
    if (length <= 2) {
        return "*".repeat(length)
    }

    // Si la palabra es larga:
    // - se muestran los primeros 2 caracteres
    // - se muestran los últimos 2 caracteres
    // - el resto se reemplaza por asteriscos
    return if (length > longWordThreshold) {
        val visibleStart = text.take(2)          // primeros 2 caracteres
        val visibleEnd = text.takeLast(2)        // últimos 2 caracteres
        val stars = "*".repeat(length - 4)       // caracteres intermedios censurados
        visibleStart + stars + visibleEnd
    } else {
        // Palabras de tamaño medio:
        // se muestra solo el primer y último carácter
        text.first() + "*".repeat(length - 2) + text.last()
    }
}
