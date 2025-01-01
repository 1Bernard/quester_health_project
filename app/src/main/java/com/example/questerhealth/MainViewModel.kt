package com.example.questerhealth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questerhealth.core.preferences.domain.usecases.AppEntryUseCases
import com.example.questerhealth.navigation.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {

    var splashCondition by mutableStateOf(true)
    private set
    var startDestination by mutableStateOf<Route>(Route.OnBoarding)
        private set

    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().collect { shouldStartFromHomeScreen ->
                startDestination = if (shouldStartFromHomeScreen) {
                    Route.Auth
                } else {
                    Route.OnBoarding
                }
                delay(1000)
                splashCondition = false
            }
        }
    }
}