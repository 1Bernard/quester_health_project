package com.example.questerhealth.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val name: String,
    val route: Route,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasChat: Boolean,
    val hasPrescription: Boolean,
    val badgeCount: Int? = null
)
