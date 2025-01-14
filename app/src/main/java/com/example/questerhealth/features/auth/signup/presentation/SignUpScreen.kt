package com.example.questerhealth.features.auth.signup.presentation

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.questerhealth.R
import com.example.questerhealth.core.presentation.components.AppTextField
import com.example.questerhealth.core.presentation.components.Checkbox
import com.example.questerhealth.core.presentation.countrycodepicker.CCPUtils
import com.example.questerhealth.core.presentation.countrycodepicker.Country
import com.example.questerhealth.core.presentation.countrycodepicker.CountryCodePickerTextField
import com.example.questerhealth.navigation.Route
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = koinViewModel(),
) {
    val isDarkTheme = isSystemInDarkTheme()
    val logoRes =
        if (isDarkTheme) painterResource(id = R.drawable.ic_logo_dark) else painterResource(id = R.drawable.ic_logo)

    val state by viewModel.state.collectAsState()
    val onAction = viewModel::onAction

    var text by remember { mutableStateOf("") }

    var country by remember {
        mutableStateOf(Country.Ghana)
    }

    // Debugging the state change
    LaunchedEffect(state) {
        Log.d("SignUpScreen", "Terms Accepted: ${state.isTermsAccepted}")
    }

    if (!LocalInspectionMode.current) {
        CCPUtils.getCountryAutomatically(context = LocalContext.current).let {
            it?.let {
                country = it
            }
        }
    }

    val isFormValid by remember(state) {
        derivedStateOf {
            val isValid = state.name.isNotBlank() &&
                    state.email.isNotBlank() &&
                    state.phoneNumber.isNotBlank() &&
                    state.password.isNotBlank() &&
                    state.confirmPassword.isNotBlank() &&
                    state.isTermsAccepted

            // Log individual states to see which condition is failing
            Log.d("SignUpScreen", "Is Form Valid: $isValid")
            Log.d("SignUpScreen", "Name: ${state.name}, Email: ${state.email}, Phone: ${state.phoneNumber}, Password: ${state.password}, ConfirmPassword: ${state.confirmPassword}, TermsAccepted: ${state.isTermsAccepted}")

            isValid
        }
    }



    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.navigationEvent.collectLatest { event ->
            when (event) {
                is SignUpViewModel.SignUpNavigationEvent.NavigateToHome -> {
                    navController.navigate(Route.Home) {
                        popUpTo(Route.SignUp) {
                            inclusive = true

                        }
                    }
                }

                is SignUpViewModel.SignUpNavigationEvent.NavigateToLogin -> {
                    navController.navigate(Route.Login)
                }

                else -> {

                }

            }
        }
    }




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Set background based on theme
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Image(
                painter = logoRes,
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back arrow",
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f) // Adjust the tint for light/dark theme
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.account_setup),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                modifier = Modifier
                    .padding(bottom = 12.dp),
                color = MaterialTheme.colorScheme.onBackground
            )

            AppTextField(
                value = state.name,
                onValueChange = {onAction(SignUpAction.ChangeName(it))},
                labelText = "Full Name",
                singleLine = true,
                isError = state.nameError != null,
                errorMessage = state.nameError,
            )
            // Email Input
            AppTextField(
                value = state.email,
                onValueChange = {onAction(SignUpAction.ChangeEmail(it))},
                labelText = "Email",
                singleLine = true,
                isError = state.emailError != null,
                errorMessage = state.emailError,
            )

            CountryCodePickerTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                enabled = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                trailingIcon = {
                    IconButton(onClick = { text = "" }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                },
                isError = state.phoneNumberError != null,
                errorMessage = state.phoneNumberError,
                shape = RoundedCornerShape(10.dp),
                onValueChange = { value, isValid ->
                    // Combine the country code and phone number
                    val fullPhoneNumber = "$value"

                    // Update the local state with the full phone number
                    text = fullPhoneNumber

                    // Notify your state management or view model
                    onAction(SignUpAction.ChangePhoneNumber(fullPhoneNumber))
                },
                number = text.removePrefix(country.countryCode), // Ensure text only contains the phone number part
                showSheet = true,
                selectedCountry = country
            )

            AppTextField(
                value = state.password,
                onValueChange = {
                    onAction(SignUpAction.ChangePassword(it))
                },
                labelText = "Password",
                isPassword = true,
                modifier = Modifier.fillMaxWidth(),
                isError = state.passwordError != null,
                errorMessage = state.passwordError
            )
            AppTextField(
                value = state.confirmPassword,
                onValueChange = {
                    onAction(SignUpAction.ChangeConfirmPassword(it))
                },
                labelText = "Confirm Password",
                isPassword = true,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                isError = state.confirmPasswordError != null,
                errorMessage = state.confirmPasswordError
            )

            Checkbox(
                checked = state.isTermsAccepted,
                text = stringResource(id = R.string.terms_and_conditions),
                onCheckedChange = { checked ->
                    onAction(SignUpAction.ChangeTermsAccepted(checked))
                },
                showError = state.isTermsAcceptedError != null,
                errorMessage = "You must agree to continue"
            )


            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f), // Disabled color
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f) // Disabled text color
                ),
                shape = MaterialTheme.shapes.extraSmall,
                enabled = isFormValid,
                onClick = {
                    viewModel.onCreateAccountClick()
                }
            ) {
                Box() {
                    AnimatedContent(
                        targetState = state.isLoading,
                        transitionSpec = {
                            fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.8f) togetherWith fadeOut(
                                animationSpec = tween(300)
                            ) + scaleOut(targetScale = 0.8f)
                        }
                    ) { target ->
                        if (target) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .padding(horizontal = 50.dp)
                                    .size(24.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(
                                text = stringResource(id = R.string.create_account),
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize
                            )
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = NavController(LocalContext.current))
}