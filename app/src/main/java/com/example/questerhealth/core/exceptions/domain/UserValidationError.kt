package com.example.questerhealth.core.exceptions.domain

sealed interface UserValidationError: Error {
    enum class Name : UserValidationError {
        EMPTY, TOO_SHORT, INVALID_CHARACTERS
    }

    enum class Email : UserValidationError {
        EMPTY, INVALID_FORMAT
    }

    enum class Phone : UserValidationError {
        EMPTY, INVALID_FORMAT
    }

    enum class Password : UserValidationError {
        TOO_SHORT, NO_UPPERCASE, NO_DIGIT, NO_SPECIAL_CHARACTER
    }
}