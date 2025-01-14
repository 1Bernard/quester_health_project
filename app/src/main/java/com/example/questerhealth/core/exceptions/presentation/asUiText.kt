package com.example.questerhealth.core.exceptions.presentation

import com.example.questerhealth.R
import com.example.questerhealth.core.exceptions.domain.DataError
import com.example.questerhealth.core.exceptions.domain.Result
import com.example.questerhealth.core.exceptions.domain.UserValidationError

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(
            R.string.the_request_timed_out
        )

        DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(
            R.string.youve_hit_your_rate_limit
        )

        DataError.Network.NO_INTERNET -> UiText.StringResource(
            R.string.no_internet
        )

        DataError.Network.PAYLOAD_TOO_LARGE -> UiText.StringResource(
            R.string.file_too_large
        )

        DataError.Network.SERVER_ERROR -> UiText.StringResource(
            R.string.server_error
        )

        DataError.Network.SERIALIZATION -> UiText.StringResource(
            R.string.error_serialization
        )

        DataError.Network.UNKNOWN -> UiText.StringResource(
            R.string.unknown_error
        )

        DataError.Local.DISK_FULL -> UiText.StringResource(
            R.string.error_disk_full
        )

    }
}

fun UserValidationError.asUiText(): UiText {
    return when (this) {
        UserValidationError.Name.EMPTY -> UiText.StringResource(R.string.name_cannot_be_empty)
        UserValidationError.Name.TOO_SHORT -> UiText.StringResource(R.string.name_too_short)
        UserValidationError.Name.INVALID_CHARACTERS -> UiText.StringResource(R.string.name_invalid_characters)

        UserValidationError.Email.EMPTY -> UiText.StringResource(R.string.email_cannot_be_empty)
        UserValidationError.Email.INVALID_FORMAT -> UiText.StringResource(R.string.invalid_email_format)

        UserValidationError.Phone.EMPTY -> UiText.StringResource(R.string.phone_cannot_be_empty)
        UserValidationError.Phone.INVALID_FORMAT -> UiText.StringResource(R.string.invalid_phone_number)

        UserValidationError.Password.TOO_SHORT -> UiText.StringResource(R.string.password_too_short)
        UserValidationError.Password.NO_UPPERCASE -> UiText.StringResource(R.string.password_no_uppercase)
        UserValidationError.Password.NO_DIGIT -> UiText.StringResource(R.string.password_no_digit)
        UserValidationError.Password.NO_SPECIAL_CHARACTER -> UiText.StringResource(R.string.password_no_special_character)

    }
}


fun Result.Error<*, DataError>.asErrorUiText(): UiText {
    return error.asUiText()
}

//fun Result.Error<*, UserValidationError>.asErrorUiText(): UiText {
//    return error.asUiText()
//}