package com.example.questerhealth.core.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.questerhealth.R
import com.example.questerhealth.core.presentation.countrycodepicker.CCPTransformer
import com.example.questerhealth.core.presentation.countrycodepicker.CountryCodePicker

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelText: String,
    placeholder: String? = null,
    isPassword: Boolean = false,
    isNumeric: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null, // Optional error message
    textStyle: TextStyle = LocalTextStyle.current,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
        errorBorderColor = MaterialTheme.colorScheme.error
    )
) {
    // Manage focus and animation
    val isFocused = remember { mutableStateOf(false) }

    // State for password visibility
    val passwordVisible = remember { mutableStateOf(false) }

    val effectiveVisualTransformation =
        if (isPassword && !passwordVisible.value) PasswordVisualTransformation() else visualTransformation

    val effectiveKeyboardOptions = when {
        isNumeric -> KeyboardOptions(keyboardType = KeyboardType.Number)
        else -> keyboardOptions
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused.value = focusState.isFocused || value.isNotEmpty()
                }
        ) {
            // Label
            Text(
                text = labelText,
                style = textStyle.copy(
                    fontSize = if (isFocused.value) 12.sp else 14.sp,
                    color = if (isError) MaterialTheme.colorScheme.error else Color.Gray
                ),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .offset(y = if (isFocused.value) (13).dp else (34).dp)
                    .background(MaterialTheme.colorScheme.background)
                    .animateContentSize()
            )

            // OutlinedTextField
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textStyle = textStyle.copy(fontWeight = FontWeight.Normal),
                placeholder = placeholder?.let {
                    { Text(it, style = textStyle.copy(color = Color.Gray)) }
                },
                isError = isError,
                visualTransformation = effectiveVisualTransformation,
                keyboardOptions = effectiveKeyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                shape = shape,
                colors = colors,
                label = {},
                trailingIcon = if (isPassword) {
                    {
                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(
                                imageVector = if (passwordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (passwordVisible.value) "Hide password" else "Show password",
                                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                            )
                        }
                    }
                } else null
            )
        }

        // Display error message if present
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style =  MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}
