package com.example.questerhealth.navigation

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun BottomNavBar(
    navController: NavHostController,
    bottomNavItems: List<BottomNavItem>
) {
    var selected by remember { mutableIntStateOf(0) }

    NavigationBar {
        bottomNavItems.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(
                selected = index == selected,
                onClick = {
                    selected = index
                    navController.navigate(bottomNavItem.route)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (bottomNavItem.badgeCount != 0) {
                                Badge {
                                    Text(text = bottomNavItem.badgeCount.toString())
                                }
                            } else if (bottomNavItem.hasChat) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (index == selected) {
                                bottomNavItem.selectedIcon
                            } else bottomNavItem.unselectedIcon,
                            contentDescription = bottomNavItem.name,
                            tint = if (index == selected) Color.Blue else Color.Unspecified
                        )
                    }
                },
                label = {
                    Text(text = bottomNavItem.name)
                }
            )
        }
    }
}




