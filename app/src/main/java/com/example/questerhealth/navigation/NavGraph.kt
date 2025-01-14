package com.example.questerhealth.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.questerhealth.features.article.HomeScreen
import com.example.questerhealth.features.auth.AuthScreen
import com.example.questerhealth.features.auth.login.LoginScreen
import com.example.questerhealth.features.auth.otp.presentation.OtpScreen
import com.example.questerhealth.features.auth.otp.presentation.OtpState
import com.example.questerhealth.features.auth.signup.presentation.SignUpScreen
import com.example.questerhealth.features.onboard.presentation.OnBoardingViewModel
import com.example.questerhealth.features.onboard.presentation.OnboardingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    startDestination: Route,

) {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        navController = navController,
        startDestination = startDestination,
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        },
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        }
    ) {

        composable<Route.OnBoarding> {
            val viewModel: OnBoardingViewModel = koinViewModel()
            OnboardingScreen(
                event = viewModel::onEvent
            )
        }
        composable<Route.Auth> {
            AuthScreen(navController = navController)
        }

        composable<Route.SignUp> {
            SignUpScreen(navController = navController)
        }

        composable<Route.Otp> {
            OtpScreen(navController = navController)
        }

        composable<Route.Login> {
            LoginScreen(navController = navController)
        }

        composable<Route.Home> {
            HomeScreen(navController = navController)
        }

    }
}