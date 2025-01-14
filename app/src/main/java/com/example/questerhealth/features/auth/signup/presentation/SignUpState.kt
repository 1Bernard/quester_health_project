package com.example.questerhealth.features.auth.signup.presentation

data class SignUpState(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isTermsAccepted: Boolean = false,
    val nameError: String? = null,
    val emailError: String? = null,
    val phoneNumberError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val isTermsAcceptedError: String? = null,
    val isLoading: Boolean = false,
    val isAccountCreated: Boolean = false
)
