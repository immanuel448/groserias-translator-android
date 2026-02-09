package com.immanuel.groseriastranslator.ui.main

import androidx.compose.material3.Switch
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import com.immanuel.groseriastranslator.domain.censor.censor
import com.immanuel.groseriastranslator.data.model.Word


@Composable
fun MainScreen(
    words: List<Word>,
    selectedWord: Word,
    translation: String,
    languageFrom: String,
    languageTo: String,
    isCensored: Boolean,
    onToggleCensorship: () -> Unit,
    onWordSelected: (Word) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Mostrar idiomas
        Text(
            text = "Idioma origen: $languageFrom",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "Idioma destino: $languageTo",
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Lista de palabras seleccionables
        LazyColumn {
            items(words) { word ->
                Text(
                    text = word.base,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { onWordSelected(word) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Original (censurada o no según isCensored)
        Text(
            text = "Original",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = if (isCensored) censor(selectedWord.base, enabled = isCensored) else selectedWord.base
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


        // traducción censurada según el switch
        Text(
            text = if (isCensored) censor(translation, enabled = true) else translation
        )

    }
}
