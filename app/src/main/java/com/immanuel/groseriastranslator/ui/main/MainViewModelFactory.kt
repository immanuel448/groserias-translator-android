package com.immanuel.groseriastranslator.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.immanuel.groseriastranslator.data.preferences.CensorshipPreferences

// Factory encargada de CREAR el MainViewModel
// Se usa porque MainViewModel tiene un constructor con parámetros
class MainViewModelFactory(

    // Dependencia que queremos inyectar al ViewModel
    // En este caso, las preferencias de censura
    private val preferences: CensorshipPreferences

) : ViewModelProvider.Factory {

    // Metodo obligatorio de la interfaz Factory
    // Android lo llama cuando necesita un ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        // Verificamos si el ViewModel solicitado
        // es del tipo MainViewModel
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            // Creamos manualmente el MainViewModel
            // pasando las dependencias necesarias
            // El cast es necesario por el uso de genéricos
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(preferences) as T
        }

        // Si se pide un ViewModel que esta Factory no conoce,
        // lanzamos un error explícito
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
