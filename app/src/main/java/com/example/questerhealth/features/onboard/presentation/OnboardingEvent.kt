package com.example.questerhealth.features.onboard.presentation

sealed class OnboardingEvent {
    data object SaveAppEntry: OnboardingEvent()

}