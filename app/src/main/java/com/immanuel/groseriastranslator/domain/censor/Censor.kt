package com.immanuel.groseriastranslator.domain.censor

fun censor(text: String, enabled: Boolean): String {
    if (!enabled) return text
    if (text.length <= 2) return "*".repeat(text.length)

    return text.first() + "*".repeat(text.length - 2) + text.last()
}
