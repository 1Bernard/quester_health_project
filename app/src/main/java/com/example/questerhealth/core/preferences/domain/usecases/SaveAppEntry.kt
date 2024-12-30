package com.example.questerhealth.core.preferences.domain.usecases

import com.example.questerhealth.core.preferences.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }

}