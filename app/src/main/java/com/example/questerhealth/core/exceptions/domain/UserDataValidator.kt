package com.example.questerhealth.core.exceptions.domain

import android.content.Context
import android.util.Patterns
import io.michaelrocks.libphonenumber.android.NumberParseException
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil

class UserDataValidator(private val context: Context) {

    fun validateName(name: String): Result<Unit, NameError> {
        return when {
            name.isBlank() -> Result.Error(NameError.EMPTY)
            name.length < 2 -> Result.Error(NameError.TOO_SHORT)
            !name.all { it.isLetter() || it.isWhitespace() } -> Result.Error(NameError.INVALID_CHARACTERS)
            else -> Result.Success(Unit)
        }
    }

    fun validateEmail(email: String): Result<Unit, EmailError> {
        return when {
            email.isBlank() -> Result.Error(EmailError.EMPTY)
            !Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() -> Result.Error(EmailError.INVALID_FORMAT)

            else -> Result.Success(Unit)
        }
    }

    fun validatePhoneNumber(phoneNumber: String, countryIso: String): Result<Unit, PhoneNumberError> {
        val phoneNumberUtil = PhoneNumberUtil.createInstance(context)
        return try {
            val parsedNumber = phoneNumberUtil.parse(phoneNumber, countryIso)
            if (phoneNumberUtil.isValidNumber(parsedNumber)) {
                Result.Success(Unit)
            } else {
                Result.Error(PhoneNumberError.INVALID_FORMAT)
            }
        } catch (e: NumberParseException) {
            Result.Error(PhoneNumberError.INVALID_FORMAT)
        }
    }

    fun validatePassword(password: String): Result<Unit, PasswordError> {
        return when {
            password.length < 9 -> Result.Error(PasswordError.TOO_SHORT)
            !password.any { it.isUpperCase() } -> Result.Error(PasswordError.NO_UPPERCASE)
            !password.any { it.isDigit() } -> Result.Error(PasswordError.NO_DIGIT)
            !password.any { !it.isLetterOrDigit() } -> Result.Error(PasswordError.NO_SPECIAL_CHARACTER)
            else -> Result.Success(Unit)
        }
    }

    // Error types
    enum class NameError : Error {
        EMPTY,
        TOO_SHORT,
        INVALID_CHARACTERS
    }

    enum class EmailError : Error {
        EMPTY,
        INVALID_FORMAT
    }

    enum class PhoneNumberError : Error {
        EMPTY,
        INVALID_FORMAT
    }

    enum class PasswordError : Error {
        TOO_SHORT,
        NO_UPPERCASE,
        NO_DIGIT,
        NO_SPECIAL_CHARACTER
    }
}
