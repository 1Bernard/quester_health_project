package com.example.questerhealth.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable data object OnBoarding : Route()
    @Serializable data object Auth : Route()
    @Serializable data object Login : Route()
    @Serializable data object SignUp : Route()
    @Serializable data object Otp : Route()
    @Serializable data object Home : Route()
    @Serializable data object MainNavigator : Route()
    @Serializable data object History : Route()
    @Serializable data object Chat : Route()
    @Serializable data object Prescription : Route()
    @Serializable data object Profile : Route()
}

