package com.immanuel.groseriastranslator.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.immanuel.groseriastranslator.data.repository.WordRepository
import com.immanuel.groseriastranslator.data.repository.TranslationRepository
import com.immanuel.groseriastranslator.ui.theme.GroseriasTranslatorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val wordRepository = WordRepository()
        val translationRepository = TranslationRepository()

        val word = wordRepository.getWords("en").first()
        val translation = translationRepository
            .getTranslations(word.id, "es")
            .first()

        setContent {
            GroseriasTranslatorTheme {
                WordScreen(
                    original = word.base,
                    translation = translation.translation,
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
