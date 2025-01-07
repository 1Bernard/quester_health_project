package com.example.questerhealth.features.auth.signup.domain

import com.example.questerhealth.core.exceptions.domain.DataError
import com.example.questerhealth.core.exceptions.domain.Result

interface SignUpRepository {
    suspend fun register(password: String): Result<SignUpRequest, DataError.Network>
}

data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String
)
