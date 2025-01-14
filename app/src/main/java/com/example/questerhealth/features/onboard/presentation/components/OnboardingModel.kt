package com.example.questerhealth.features.onboard.presentation.components

import androidx.annotation.DrawableRes
import com.example.questerhealth.R

sealed class OnboardingModel(
    @DrawableRes val logo: Int = R.drawable.ic_logo,
    @DrawableRes val darkThemeLogo: Int = R.drawable.ic_logo_dark,
    @DrawableRes val image: Int,
    val title: Int,
    val desc: Int
) {
    data object Firstpage: OnboardingModel(
        image = R.drawable.onboarding_one,
        title = R.string.onboarding_title_one,
        desc = R.string.onboarding_desc_one
    )

    data object Secondpage: OnboardingModel(
        image = R.drawable.onboarding_two,
        title = R.string.onboarding_title_two,
        desc = R.string.onboarding_desc_two
    )

    data object Thirdpage: OnboardingModel(
        image = R.drawable.onboarding_three,
        title = R.string.onboarding_title_three,
        desc = R.string.onboarding_desc_three
    )
}