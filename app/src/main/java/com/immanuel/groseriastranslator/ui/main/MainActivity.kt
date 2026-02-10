package com.immanuel.groseriastranslator.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.immanuel.groseriastranslator.data.preferences.CensorshipPreferences
import com.immanuel.groseriastranslator.ui.theme.GroseriasTranslatorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Creamos la instancia de preferencias
        val preferences = CensorshipPreferences(applicationContext)

        setContent {
            GroseriasTranslatorTheme {

                // Creamos el ViewModel y le inyectamos las preferencias
                val viewModel: MainViewModel = viewModel(
                    factory = MainViewModelFactory(preferences)
                )

                // Obtenemos los datos del ViewModel como State para Compose
                val selectedVariant by viewModel.selectedVariant.collectAsState()
                val translation by viewModel.translation.collectAsState()
                val isCensored by viewModel.isCensored.collectAsState()

                // Pasamos los datos y callbacks a la UI
                MainScreen(
                    words = viewModel.words,
                    selectedVariant = selectedVariant,
                    translation = translation.variants.joinToString(", ") { it.text }, // concatenamos traducciones
                    languageFrom = selectedVariant.text, // aquí puedes usar selectedVariant.text o selectedWord.language según prefieras
                    languageTo = translation.languageTo,
                    isCensored = isCensored,
                    onToggleCensorship = { viewModel.toggleCensorship() },
                    onWordSelected = { viewModel.selectWord(it) }
                )
            }
        }
    }
}
