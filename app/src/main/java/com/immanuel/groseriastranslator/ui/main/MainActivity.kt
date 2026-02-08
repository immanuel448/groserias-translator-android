package com.immanuel.groseriastranslator.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.immanuel.groseriastranslator.ui.theme.GroseriasTranslatorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GroseriasTranslatorTheme {

                // Compose crea y gestiona el ViewModel
                val viewModel: MainViewModel = viewModel()

                MainScreen(
                    original = viewModel.word.base,
                    translation = viewModel.translation.translation,
                    languageFrom = viewModel.word.language,
                    languageTo = viewModel.translation.languageTo,
                    isCensored = true // luego será preferencia del usuario
                )
            }
        }
    }
}
