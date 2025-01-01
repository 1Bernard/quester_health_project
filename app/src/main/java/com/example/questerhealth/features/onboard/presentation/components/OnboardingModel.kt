package com.example.questerhealth.features.onboard.presentation.components

import androidx.annotation.DrawableRes
import com.example.questerhealth.R

sealed class OnboardingModel(
    @DrawableRes val logo: Int = R.drawable.ic_logo,
    @DrawableRes val darkThemeLogo: Int = R.drawable.ic_logo_dark,
    @DrawableRes val image: Int,
    val title: String,
    val desc: String
) {
    data object Firstpage: OnboardingModel(
        image = R.drawable.onboarding_one,
        title = "Find a Doctor",
        desc = "Find a doctor in the comfort of your home/office."
    )

    data object Secondpage: OnboardingModel(
        image = R.drawable.onboarding_two,
        title = "The Choice is Yours",
        desc = "Contact a doctor via video call, voice call or chat."
    )

    data object Thirdpage: OnboardingModel(
        image = R.drawable.onboarding_three,
        title = "Online Appointment Made Easier",
        desc = "Book appointments with doctors at the comfort of your home."
    )
}