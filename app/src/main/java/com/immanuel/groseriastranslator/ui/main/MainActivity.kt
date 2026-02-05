package com.immanuel.groseriastranslator.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.immanuel.groseriastranslator.data.repository.WordRepository
import com.immanuel.groseriastranslator.ui.theme.GroseriasTranslatorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = WordRepository()
        val word = repository.getWords().first()

        setContent {
            GroseriasTranslatorTheme {
                WordScreen(
                    original = word.original,
                    translation = word.translation,
                    censored = word.censored
                )
            }
        }
    }
}

@Composable
fun WordScreen(
    original: String,
    translation: String,
    censored: String
) {
    Text(text = "$original → $translation ($censored)")
}
