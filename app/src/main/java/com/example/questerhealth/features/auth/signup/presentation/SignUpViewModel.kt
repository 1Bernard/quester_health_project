package com.example.questerhealth.features.auth.signup.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questerhealth.core.exceptions.domain.UserDataValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.example.questerhealth.core.exceptions.domain.Result
import com.example.questerhealth.core.exceptions.presentation.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class SignUpViewModel(
    private val validator: UserDataValidator,
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<SignUpEvent>()
    val events = eventChannel.receiveAsFlow()

    private val _navigationEvent = MutableSharedFlow<SignUpNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.ChangeName -> {
                _state.value = _state.value.copy(name = action.newName)
            }

            is SignUpAction.ChangeEmail -> {
                _state.value = _state.value.copy(email = action.newEmail)
            }

            is SignUpAction.ChangePhoneNumber -> {
                _state.value = _state.value.copy(phoneNumber = action.newPhoneNumber, countryIso = action.countryIso)
            }

            is SignUpAction.ChangePassword -> {
                _state.value = _state.value.copy(password = action.newPassword)
            }

            is SignUpAction.ChangeConfirmPassword -> {
                _state.value = _state.value.copy(confirmPassword = action.newConfirmPassword)
            }

            is SignUpAction.ChangeTermsAccepted -> {
                _state.value = _state.value.copy(isTermsAccepted = action.newIsAccepted)
            }

            SignUpAction.CreateAccount -> onCreateAccountClick()

        }
    }

    fun onCreateAccountClick() {
        val currentState = _state.value
        var hasErrors = false

        // Validate name
        when (val nameResult = validator.validateName(currentState.name)) {
            is Result.Error -> {
                hasErrors = true
                _state.value = _state.value.copy(
                    nameError = when (nameResult.error) {
                        UserDataValidator.NameError.EMPTY -> "Name cannot be empty"
                        UserDataValidator.NameError.TOO_SHORT -> "Name is too short"
                        UserDataValidator.NameError.INVALID_CHARACTERS -> "Name contains invalid characters"
                    }
                )
            }
            is Result.Success -> {
                _state.value = _state.value.copy(nameError = null)
            }
        }

        // Validate email
        when (val emailResult = validator.validateEmail(currentState.email)) {
            is Result.Error -> {
                hasErrors = true
                _state.value = _state.value.copy(
                    emailError = when (emailResult.error) {
                        UserDataValidator.EmailError.EMPTY -> "Email cannot be empty"
                        UserDataValidator.EmailError.INVALID_FORMAT -> "Invalid email format"
                    }
                )
            }
            is Result.Success -> {
                _state.value = _state.value.copy(emailError = null)
            }
        }

        // Validate phone number
        when (val phoneNumberResult = validator.validatePhoneNumber(currentState.phoneNumber, currentState.countryIso)) {
            is Result.Error -> {
                hasErrors = true
                _state.value = _state.value.copy(
                    phoneNumberError = when (phoneNumberResult.error) {
                        UserDataValidator.PhoneNumberError.EMPTY -> "Phone number cannot be empty"
                        UserDataValidator.PhoneNumberError.INVALID_FORMAT -> "Invalid phone number format"
                    }
                )
            }
            is Result.Success -> {
                _state.value = _state.value.copy(phoneNumberError = null)
            }
        }

        // Validate password
        when (val passwordResult = validator.validatePassword(currentState.password)) {
            is Result.Error -> {
                hasErrors = true
                _state.value = _state.value.copy(
                    passwordError = when (passwordResult.error) {
                        UserDataValidator.PasswordError.TOO_SHORT -> "Password is too short"
                        UserDataValidator.PasswordError.NO_UPPERCASE -> "Password must contain at least one uppercase letter"
                        UserDataValidator.PasswordError.NO_DIGIT -> "Password must contain at least one digit"
                        UserDataValidator.PasswordError.NO_SPECIAL_CHARACTER -> "Password must contain at least one special character"
                    }
                )
            }
            is Result.Success -> {
                _state.value = _state.value.copy(passwordError = null)
            }
        }

        // Validate confirm password
        if (currentState.password != currentState.confirmPassword) {
            hasErrors = true
            _state.value = _state.value.copy(confirmPasswordError = "Passwords do not match")
        } else {
            _state.value = _state.value.copy(confirmPasswordError = null)
        }

        // Validate terms acceptance
        if (!currentState.isTermsAccepted) {
            hasErrors = true
            _state.value = _state.value.copy(isTermsAcceptedError = "You must accept the terms and conditions")
        } else {
            _state.value = _state.value.copy(isTermsAcceptedError = null)
        }

        // Log errors for debugging
        if (hasErrors) {
            Log.d("SignUp", "Validation failed: $currentState")
        }

        // Proceed if no errors
        if (!hasErrors) {
            Log.d("SignUp", "Validation succeeded: Navigating to home screen")
            viewModelScope.launch {
                _state.value = currentState.copy(isLoading = true)
                kotlinx.coroutines.delay(2000) // Simulate account creation process
                _state.value = currentState.copy(isLoading = false, isAccountCreated = true)
                _navigationEvent.emit(SignUpNavigationEvent.NavigateToOtp)
            }
        }
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(SignUpNavigationEvent.NavigateToLogin)
        }
    }

    sealed class SignUpNavigationEvent {
        data object NavigateToLogin : SignUpNavigationEvent()
        data object NavigateToOtp : SignUpNavigationEvent()
    }

}


sealed interface SignUpEvent {
    data class Error(val error: UiText) : SignUpEvent
}
