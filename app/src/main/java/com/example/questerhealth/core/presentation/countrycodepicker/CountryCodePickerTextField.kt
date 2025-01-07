package com.example.questerhealth.core.presentation.countrycodepicker

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CountryCodePickerTextField(
    value: String? = null,
    modifier: Modifier = Modifier,
    number: String,
    onValueChange: (countryCode: String, value: String, isValid: Boolean) -> Unit,
    enabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    labelText: String = "Phone number",
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    showError: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    shape: Shape = RoundedCornerShape(10.dp),
    selectedCountry: Country = Country.Bangladesh,
    countryList: List<Country> = Country.getAllCountries(),
    viewCustomization: ViewCustomization = ViewCustomization(),
    pickerCustomization: PickerCustomization = PickerCustomization(),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
//        unfocusedBorderColor = Color.LightGray.copy(alpha = 0.4f),
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
        errorBorderColor = MaterialTheme.colorScheme.error
    ),
    showSheet: Boolean = false,
    itemPadding: Int = 10,
) {
    // Manage focus state
    val isFocused = remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused.value = focusState.isFocused || number.isNotEmpty()
            }
    ) {
        // Label
        Text(
            text = labelText,
            style = textStyle.copy(
                fontSize = if (isFocused.value || number.isNotEmpty()) 12.sp else 14.sp,
                color = if (isError) MaterialTheme.colorScheme.error else Color.Gray
            ),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .offset(
                    y = if (isFocused.value || number.isNotEmpty()) (11).dp else (26).dp,
                    x = if (isFocused.value) (1).dp else (97).dp
                )
                .background(MaterialTheme.colorScheme.background)
                .animateContentSize()
        )

        val context = LocalContext.current

        var country by remember {
            mutableStateOf(selectedCountry)
        }

        val validatePhoneNumber = remember(context) {
            CCPValidator(context = context)
        }

        var isNumberValid by rememberSaveable(country, number) {
            mutableStateOf(
                validatePhoneNumber(
                    number = number, countryCode = country.countryCode
                ),
            )
        }

        // Outlined Text Field
        OutlinedTextField(
            value = number,
            onValueChange = {
                isNumberValid = validatePhoneNumber(
                    number = it, countryCode = country.countryCode
                )
                onValueChange(country.countryCode, it, isNumberValid)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textStyle = textStyle,
            singleLine = true,
            shape = shape,
            placeholder = placeholder,
            leadingIcon = {
                CountryCodePicker(
                    selectedCountry = country,
                    countryList = countryList,
                    onCountrySelected = {
                        country = it
                        isNumberValid = validatePhoneNumber(
                            number = number, countryCode = it.countryCode
                        )
                        onValueChange(it.countryCode, number, isNumberValid)
                    },
                    viewCustomization = viewCustomization,
                    pickerCustomization = pickerCustomization,
                    backgroundColor = backgroundColor,
                    textStyle = textStyle,
                    showSheet = showSheet,
                    itemPadding = itemPadding
                )
            },
            trailingIcon = trailingIcon,
            isError = !isNumberValid && number.isNotEmpty() && showError,
            visualTransformation = CCPTransformer(context, country.countryIso),
            enabled = enabled,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            colors = colors
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    var value by remember {
        mutableStateOf("")
    }

    CountryCodePickerTextField(onValueChange = { _, number, _ ->
        value = number

    }, number = value)
}