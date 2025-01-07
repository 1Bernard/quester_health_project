package com.example.questerhealth.features.auth.signup.data

import com.example.questerhealth.core.exceptions.domain.DataError
import com.example.questerhealth.core.exceptions.domain.Result
import com.example.questerhealth.features.auth.signup.domain.SignUpRepository
import com.example.questerhealth.features.auth.signup.domain.SignUpRequest

class SignUpRepositoryImpl: SignUpRepository {
    override suspend fun register(password: String): Result<SignUpRequest, DataError.Network> {
        TODO("Not yet implemented")
    }
}