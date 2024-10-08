package com.example.medicinereminder.common.components.text_field

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun OutlinedTextFieldComponent(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    @StringRes errorMessage: Int? =null,
    isRequired: Boolean = false,
    @StringRes buttonText: Int? = null,
    @DrawableRes buttonIcon: Int? = null,
    onTextButtonClick: () -> Unit = {},
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        isError = errorMessage!=null,
        supportingText = {
            if (errorMessage!=null) {
                Text(
                    text = stringResource(errorMessage),
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        maxLines = 1,
        label = {
            Text(
                text = if (isRequired)
                    stringResource(label).plus(" *")
                else stringResource(label)
            )
        },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        trailingIcon = {
            buttonText?.let {
                TextButton(
                    onClick = { onTextButtonClick() },
                    modifier = Modifier.padding(end = MaterialTheme.spacing.small8)
                ) {
                    Text(
                        text = stringResource(it),
                        style = TextStyle(
                            fontWeight = FontWeight.W400,
                        )
                    )
                }
            }
            buttonIcon?.let {
                Icon(
                    imageVector = ImageVector.vectorResource(id = it),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
    )
}

@DarkAndLightModePreview
@Composable
fun MedicineReminderTextFieldPreview() {
    MedicineReminderTheme {
        Surface {
            OutlinedTextFieldComponent(
                value = "Hello",
                onValueChange = {},
                label = R.string.medicine_name,
                isRequired = true
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun MedicineReminderTextFieldWithTextActionPreview() {
    MedicineReminderTheme {
        Surface {
            OutlinedTextFieldComponent(
                value = "Hello",
                onValueChange = {},
                label = R.string.medicine_name,
                isRequired = true,
                buttonText = R.string.save,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun MedicineReminderTextFieldWithIconActionPreview() {
    MedicineReminderTheme {
        Surface {
            OutlinedTextFieldComponent(
                value = "Hello",
                onValueChange = {},
                label = R.string.medicine_name,
                isRequired = true,
                buttonIcon = Icons.Outlined.ArrowDropDown,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun MedicineReminderTextFieldErrorPreview() {
    MedicineReminderTheme {
        Surface {
            OutlinedTextFieldComponent(
                value = "Hello",
                onValueChange = {},
                label = R.string.medicine_name,
                isRequired = true,
                errorMessage = R.string.required_field,
                buttonText = R.string.save,
            )
        }
    }
}
