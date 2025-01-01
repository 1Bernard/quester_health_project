package com.example.questerhealth.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable data object OnBoarding : Route()
    @Serializable data object Auth : Route()
    @Serializable data object Login : Route()
    @Serializable data object SignUp : Route()
    @Serializable data object Home : Route()
    @Serializable data object Profile : Route()
}

