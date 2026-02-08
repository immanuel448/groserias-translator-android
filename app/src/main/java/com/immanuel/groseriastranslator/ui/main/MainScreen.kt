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
import com.immanuel.groseriastranslator.domain.censor.censor


@Composable
fun MainScreen(
    original: String,
    translation: String,
    languageFrom: String,
    languageTo: String,
    isCensored: Boolean,
    onToggleCensorship: () -> Unit
)
{
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

        Text(
            text = "Original",
            style = MaterialTheme.typography.titleMedium
        )
        Text(text = original)

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Traducción",
            style = MaterialTheme.typography.titleMedium
        )
        Text(text = translation)

        Spacer(modifier = Modifier.height(12.dp))

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
                text = original,
                enabled = isCensored
            )
        )
    }
}
