package com.immanuel.groseriastranslator.ui.main

import android.os.Bundle
import androidx.compose.runtime.collectAsState
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

                // Pasamos datos + acciones a la UI
                val selectedWord by viewModel.selectedWord.collectAsState()
                val translation by viewModel.translation.collectAsState()

                MainScreen(
                    words = viewModel.words,
                    selectedWord = selectedWord,
                    translation = translation.translation,
                    languageFrom = selectedWord.language,
                    languageTo = translation.languageTo,
                    isCensored = viewModel.isCensored.collectAsState().value,
                    onToggleCensorship = { viewModel.toggleCensorship() },
                    onWordSelected = { viewModel.selectWord(it) }
                )

            }
        }
    }
}
