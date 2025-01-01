package com.example.questerhealth.core.preferences.domain.usecases

import com.example.questerhealth.core.preferences.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }

}