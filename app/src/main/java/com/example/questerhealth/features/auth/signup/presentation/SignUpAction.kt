package com.example.questerhealth.features.auth.signup.presentation

import com.example.questerhealth.core.exceptions.presentation.UiText

sealed interface SignUpAction {
    data class ChangeName(val newName: String) : SignUpAction
    data class ChangeEmail(val newEmail: String) : SignUpAction
    data class ChangePhoneNumber(val newPhoneNumber: String, val countryIso: String) : SignUpAction
    data class ChangePassword(val newPassword: String) : SignUpAction
    data class ChangeConfirmPassword(val newConfirmPassword: String) : SignUpAction
    data class ChangeTermsAccepted(val newIsAccepted: Boolean) : SignUpAction
    data object CreateAccount : SignUpAction
}