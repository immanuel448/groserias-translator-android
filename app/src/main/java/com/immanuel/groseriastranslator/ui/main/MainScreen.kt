package com.immanuel.groseriastranslator.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.immanuel.groseriastranslator.domain.censor.censor
import com.immanuel.groseriastranslator.data.model.word.Word
import com.immanuel.groseriastranslator.data.model.word.WordVariant
import com.immanuel.groseriastranslator.data.model.common.VariantType

@Composable
fun MainScreen(
    words: List<Word>,
    selectedVariant: WordVariant,
    translation: String,
    languageFrom: String,
    languageTo: String,
    isCensored: Boolean,
    onToggleCensorship: () -> Unit,
    onWordSelected: (Word) -> Unit,
    onVariantSelected: (WordVariant) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Idioma origen: $languageFrom",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "Idioma destino: $languageTo",
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.height(24.dp))


        // Ejemplos
        Text(
            text = "Selecciona la palabra",
            style = MaterialTheme.typography.titleMedium
        )

        // Lista de conceptos
        LazyColumn {
            items(words) { word ->
                Text(
                    text = word.variants.first().text,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { onWordSelected(word) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Variantes del concepto seleccionado
        Text(
            text = "Variantes",
            style = MaterialTheme.typography.titleMedium
        )

        val currentWord =
            words.firstOrNull { it.variants.contains(selectedVariant) }

        currentWord?.variants?.forEach { variant ->
            val label =
                if (variant.variantType == VariantType.BASE)
                    "${variant.text} (base)"
                else
                    "${variant.text} (sinónimo)"

            Text(
                text = label,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onVariantSelected(variant) }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Original
        Text(
            text = "Original",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = if (isCensored)
                censor(selectedVariant.text, enabled = true)
            else
                selectedVariant.text
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Traducción
        Text(
            text = "Traducción",
            style = MaterialTheme.typography.titleMedium
        )
        Text(text = translation)

        Spacer(modifier = Modifier.height(12.dp))

        // Censura
        Text(
            text = "Censura",
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Censura activada")
            Switch(
                checked = isCensored,
                onCheckedChange = { onToggleCensorship() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = censor(
                text = translation,
                enabled = isCensored
            )
        )
    }
}
