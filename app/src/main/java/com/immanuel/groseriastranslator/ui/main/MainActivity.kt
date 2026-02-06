package com.immanuel.groseriastranslator.ui.main

// Clases base de Android
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// Componente visual simple de Compose
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// Repositorios: capa de datos
import com.immanuel.groseriastranslator.data.repository.WordRepository
import com.immanuel.groseriastranslator.data.repository.TranslationRepository

// Tema visual de la app
import com.immanuel.groseriastranslator.ui.theme.GroseriasTranslatorTheme

// Activity principal: punto de entrada de la app
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Se crean los repositorios
        // En este punto estamos simulando una fuente de datos
        val wordRepository = WordRepository()
        val translationRepository = TranslationRepository()

        // 2. Obtenemos una palabra base en inglés
        // getWords("en") devuelve todas las palabras en inglés
        // first() solo toma la primera (temporal, para pruebas)
        val word = wordRepository.getWords("en").first()

        // 3️. Obtenemos la traducción de ESA palabra al español
        // Usamos word.id para asociar correctamente
        val translation = translationRepository
            .getTranslations(word.id, "es")
            .first()

        // 4️. Definimos el contenido visual con Jetpack Compose
        setContent {
            // Tema global de la app
            GroseriasTranslatorTheme {
                // Pantalla simple que muestra la información
                WordScreen(
                    original = word.base,              // palabra original
                    translation = translation.translation, // traducción
                    censored = word.censored           // versión censurada
                )
            }
        }
    }
}

// 5️. Componente visual reutilizable
@Composable
fun WordScreen(
    original: String,
    translation: String,
    censored: String
) {
    // Muestra la palabra, su traducción y la versión censurada
    Text(text = "$original → $translation ($censored)")
}
