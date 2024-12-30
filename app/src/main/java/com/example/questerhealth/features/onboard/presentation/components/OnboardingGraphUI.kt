package com.example.questerhealth.features.onboard.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.questerhealth.features.onboard.presentation.OnboardingModel

@Composable
fun OnboardingGraphUI(onboardingModel: OnboardingModel) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        // Logo at the top
        Image(
            painter = painterResource(id = onboardingModel.logo),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp),
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Onboarding image in the middle
        Image(
            painter = painterResource(id = onboardingModel.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
        // Title
        Text(
            text = onboardingModel.title,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Description
        Text(
            text = onboardingModel.desc,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingGraphUIPreview1() {
    OnboardingGraphUI(onboardingModel = OnboardingModel.Firstpage)
}

@Preview(showBackground = true)
@Composable
fun OnboardingGraphUIPreview2() {
    OnboardingGraphUI(onboardingModel = OnboardingModel.Secondpage)
}

@Preview(showBackground = true)
@Composable
fun OnboardingGraphUIPreview3() {
    OnboardingGraphUI(onboardingModel = OnboardingModel.Thirdpage)
}

