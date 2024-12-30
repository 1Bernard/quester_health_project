package com.example.questerhealth.features.onboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questerhealth.core.preferences.domain.usecases.AppEntryUseCases
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {

    fun onEvent(event: OnboardingEvent) {
        when(event) {
            is OnboardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}

