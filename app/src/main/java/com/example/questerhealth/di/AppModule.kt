package com.example.questerhealth.di

import com.example.questerhealth.MainViewModel
import com.example.questerhealth.core.preferences.data.manager.LocalUserManagerImpl
import com.example.questerhealth.core.preferences.domain.manager.LocalUserManager
import com.example.questerhealth.core.preferences.domain.usecases.AppEntryUseCases
import com.example.questerhealth.core.preferences.domain.usecases.ReadAppEntry
import com.example.questerhealth.core.preferences.domain.usecases.SaveAppEntry
import com.example.questerhealth.features.onboard.presentation.OnBoardingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<LocalUserManager> { LocalUserManagerImpl(androidContext()) }
    single { AppEntryUseCases(readAppEntry = ReadAppEntry(get()), saveAppEntry = SaveAppEntry(get())) }

    viewModel {
        OnBoardingViewModel(get())
    }
    viewModel {
        MainViewModel(get()) // Ensure MainViewModel is available for injection
    }

}