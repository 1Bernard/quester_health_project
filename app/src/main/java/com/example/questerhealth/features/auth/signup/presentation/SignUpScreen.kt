package com.example.questerhealth.features.auth.signup.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.questerhealth.R
import com.example.questerhealth.core.presentation.components.AppTextField
import com.example.questerhealth.core.presentation.countrycodepicker.CountryCodePickerTextField
import com.example.questerhealth.core.presentation.countrycodepicker.CountryCodeView
import com.example.questerhealth.core.presentation.countrycodepicker.ShowCountryCodePickerTextField

@Composable
fun SignUpScreen(navController: NavController) {
    val isDarkTheme = isSystemInDarkTheme()
    val logoRes =
        if (isDarkTheme) painterResource(id = R.drawable.ic_logo_dark) else painterResource(id = R.drawable.ic_logo)

    var textFieldName by remember { mutableStateOf("") }
    var textFieldPassword by remember { mutableStateOf("") }
    var textFieldConfirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Set background based on theme
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Image(
                painter = logoRes,
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            IconButton(
                onClick = {navController.popBackStack()},
                modifier = Modifier
                    .align(Alignment.Start)
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Arrow left",
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.account_setup),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                modifier = Modifier
                    .padding(bottom = 12.dp),
                color = MaterialTheme.colorScheme.onBackground
            )

            AppTextField(
                value = textFieldName,
                onValueChange = {
                    textFieldName = it
                },
                labelText = stringResource(id = R.string.full_name),
                modifier = Modifier.fillMaxWidth()
            )

            ShowCountryCodePickerTextField()

            AppTextField(
                value = textFieldPassword,
                onValueChange = {
                    textFieldPassword = it
                },
                labelText = "Password",
                isPassword = true,
                modifier = Modifier.fillMaxWidth()
            )
            AppTextField(
                value = textFieldConfirmPassword,
                onValueChange = {
                    textFieldConfirmPassword = it
                },
                labelText = "Confirm Password",
                isPassword = true,
                modifier = Modifier.fillMaxWidth()
            )




        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = NavController(LocalContext.current))
}