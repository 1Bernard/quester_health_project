package com.example.questerhealth.features.auth.otp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.questerhealth.features.auth.otp.presentation.component.OtpInputField
import com.example.questerhealth.features.auth.signup.presentation.SignUpViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OtpScreen(
    navController: NavController,
    viewModel: OtpViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onAction = viewModel::onAction

    // Focus management
    val focusRequesters = remember { List(4) { FocusRequester() } }
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current

    // Automatically request focus when the focusedIndex changes
    LaunchedEffect(state.focusedIndex) {
        state.focusedIndex?.let { index ->
            focusRequesters.getOrNull(index)?.requestFocus()
        }
    }

    // Handle when all OTP fields are filled
    LaunchedEffect(state.code, keyboardManager) {
        val allNumbersEntered = state.code.none { it == null }
        if (allNumbersEntered) {
            focusRequesters.forEach { it.freeFocus() }
            focusManager.clearFocus()
            keyboardManager?.hide()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            state.code.forEachIndexed { index, number ->
                OtpInputField(
                    number = number,
                    focusRequester = focusRequesters[index],
                    onFocusChanged = { isFocused ->
                        if (isFocused) {
                            onAction(OtpAction.OnChangeFieldFocused(index))
                        }
                    },
                    onNumberChanged = { newNumber ->
                        onAction(OtpAction.OnEnterNumber(newNumber, index))
                    },
                    onKeyboardBack = {
                        onAction(OtpAction.OnKeyboardBack)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                )
            }
        }

        state.isValid?.let { isValid ->
            Text(
                text = if (isValid) "OTP is valid!" else "OTP is invalid!",
                color = if (isValid) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                fontSize = 16.sp
            )
        }
    }
}
