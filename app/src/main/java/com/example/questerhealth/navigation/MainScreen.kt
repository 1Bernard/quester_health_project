package com.example.questerhealth.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.EventNote
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.automirrored.outlined.EventNote
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.questerhealth.features.article.HomeScreen
import com.example.questerhealth.features.auth.AuthScreen
import com.example.questerhealth.features.chat.ChatScreen
import com.example.questerhealth.features.history.HistoryScreen
import com.example.questerhealth.features.prescription.PrescriptionScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Home",
            route = Route.Home,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasChat = false,
            hasPrescription = false,
            badgeCount = 0
        ),
        BottomNavItem(
            name = "History",
            route = Route.History,
            selectedIcon = Icons.AutoMirrored.Filled.EventNote,
            unselectedIcon = Icons.AutoMirrored.Outlined.EventNote,
            hasChat = false,
            hasPrescription = false,
            badgeCount = 0
        ),
        BottomNavItem(
            name = "Chats",
            route = Route.Chat,
            selectedIcon = Icons.AutoMirrored.Filled.Chat,
            unselectedIcon = Icons.AutoMirrored.Outlined.Chat,
            hasChat = true,
            hasPrescription = false,
            badgeCount = 3
        ),
        BottomNavItem(
            name = "Prescription",
            route = Route.Prescription,
            selectedIcon = Icons.AutoMirrored.Filled.ListAlt,
            unselectedIcon = Icons.AutoMirrored.Outlined.ListAlt,
            hasChat = false,
            hasPrescription = true,
            badgeCount = 1
        )
    )

    Scaffold(
        topBar = {

        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                bottomNavItems = bottomNavItems
            )
        }

    ){
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.Home,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable<Route.Home> {
                HomeScreen(navController = navController)
            }

            composable<Route.History> {
                HistoryScreen(navController = navController)
            }

            composable<Route.Chat> {
                ChatScreen(navController = navController)
            }

            composable<Route.Prescription> {
                PrescriptionScreen(navController = navController)
            }
        }
    }

}
