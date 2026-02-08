package com.immanuel.groseriastranslator.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.immanuel.groseriastranslator.data.repository.WordRepository
import com.immanuel.groseriastranslator.data.repository.TranslationRepository
import com.immanuel.groseriastranslator.ui.theme.GroseriasTranslatorTheme

//veo que los archivos en main no se importan, este no importa a mainscreen  ni a la inversa

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //aqui se optienen todos los datos contenidos en los repositorios?
        val wordRepository = WordRepository()
        val translationRepository = TranslationRepository()

        val word = wordRepository.getWords("en").first()
        val translation = translationRepository
            .getTranslations(word.id, "es")
            .first()

        //esta fucnion esta en mainscreen como se usan sus fucnines sin un objeto?, tampoco se importo

        setContent {
            GroseriasTranslatorTheme {
                MainScreen(
                    original = word.base,
                    translation = translation.translation,
                    censored = word.censored,
                    languageFrom = word.language,
                    languageTo = translation.languageTo
                )
            }
        }
    }
}
