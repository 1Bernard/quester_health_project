package com.example.questerhealth.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MultiStepForm() {
    // Track the current step (1-based index)
    var currentStep by remember { mutableIntStateOf(1) }
    val totalSteps = 3

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        // Progress Bar
        Column {
            Text(
                text = "Step $currentStep of $totalSteps",
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            LinearProgressIndicator(
                progress = { currentStep / totalSteps.toFloat() },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
        }

        // Form Content (Different for Each Step)
        when (currentStep) {
            1 -> PersonalInfoForm()
            2 -> LifestyleForm()
            3 -> MedicalBackgroundForm()
        }

        // Navigation Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (currentStep > 1) {
                Button(onClick = { currentStep-- }) {
                    Text("Back")
                }
            }
            if (currentStep < totalSteps) {
                Button(onClick = { currentStep++ }) {
                    Text("Next")
                }
            } else {
                Button(onClick = { /* Submit form logic */ }) {
                    Text("Done")
                }
            }
        }
    }
}

@Composable
fun PersonalInfoForm() {
    Column {
        TextField(value = "", onValueChange = {}, label = { Text("Date of Birth") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = "", onValueChange = {}, label = { Text("Sex") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = "", onValueChange = {}, label = { Text("Height (m)") })
    }
}

@Composable
fun LifestyleForm() {
    Column {
        TextField(value = "", onValueChange = {}, label = { Text("Occupation") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = "", onValueChange = {}, label = { Text("Do you smoke?") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = "", onValueChange = {}, label = { Text("How often do you smoke?") })
    }
}

@Composable
fun MedicalBackgroundForm() {
    Column {
        TextField(value = "", onValueChange = {}, label = { Text("Allergies") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = "", onValueChange = {}, label = { Text("Chronic Diseases") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = "", onValueChange = {}, label = { Text("Blood transfusion") })
    }
}

@Preview(showBackground = true)
@Composable
fun MultiStepFormPreview() {
    MultiStepForm()
}