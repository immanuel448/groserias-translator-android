package com.immanuel.groseriastranslator.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.immanuel.groseriastranslator.data.preferences.CensorshipPreferences

class MainViewModelFactory(
    private val preferences: CensorshipPreferences
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(preferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
