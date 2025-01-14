package com.example.questerhealth.features.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.questerhealth.R
import com.example.questerhealth.core.presentation.components.AppTextField
import com.example.questerhealth.core.presentation.components.Checkbox
import com.example.questerhealth.core.presentation.countrycodepicker.CCPUtils
import com.example.questerhealth.core.presentation.countrycodepicker.Country
import com.example.questerhealth.core.presentation.countrycodepicker.CountryCodePickerTextField
import com.example.questerhealth.navigation.Route

@Composable
fun LoginScreen(navController: NavController) {
    val isDarkTheme = isSystemInDarkTheme()
    val logoRes =
        if (isDarkTheme) painterResource(id = R.drawable.ic_logo_dark) else painterResource(id = R.drawable.ic_logo)

    val text = stringResource(id = R.string.dont_have_account)
    val splitText = text.split("Signup")

    var textFieldEmail by remember { mutableStateOf("") }
    var textFieldPassword by remember { mutableStateOf("") }

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

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back arrow",
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f) // Adjust the tint for light/dark theme
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                modifier = Modifier
                    .padding(bottom = 12.dp),
                color = MaterialTheme.colorScheme.onBackground
            )

            // Email Input
            AppTextField(
                value = textFieldEmail,
                onValueChange = { textFieldEmail = it },
                labelText = "Email",
                singleLine = true // Ensure single-line input
            )

            AppTextField(
                value = textFieldPassword,
                onValueChange = {
                    textFieldPassword = it
                },
                labelText = "Password",
                isPassword = true,
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = MaterialTheme.shapes.extraSmall,
                onClick = {
                    navController.navigate(Route.Home)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(splitText[0])
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append("Signup")
                        }
                    },
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 12.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Route.SignUp)
                        },
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = stringResource(id = R.string.or),
                    modifier = Modifier
                        .padding(bottom = 12.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = stringResource(id = R.string.continue_with),
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        contentPadding = PaddingValues(vertical = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        shape = MaterialTheme.shapes.extraSmall,
                        onClick = {

                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = "Google Logo",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }

                    Button(
                        contentPadding = PaddingValues(vertical = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        shape = MaterialTheme.shapes.extraSmall,
                        onClick = {

                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_facebook),
                            contentDescription = "Google Logo",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }

                    Button(
                        contentPadding = PaddingValues(vertical = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        shape = MaterialTheme.shapes.extraSmall,
                        onClick = {

                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_apple),
                            contentDescription = "Google Logo",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }

                }
            }
        }

    }
}
