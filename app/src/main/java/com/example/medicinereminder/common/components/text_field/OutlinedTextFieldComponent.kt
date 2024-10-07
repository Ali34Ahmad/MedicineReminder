package com.example.medicinereminder.common.components.text_field

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun OutlinedTextFieldComponent(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    isError: Boolean = false,
    isRequired: Boolean = false,
    buttonText: String? = null,
    @DrawableRes buttonIcon: Int? = null,
    onClick: () -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        isError = isError,
        maxLines = 1,
        label = {
            Text(text = if (isRequired)
                stringResource(label).plus(" *")
            else stringResource(label)
            )
        },
        trailingIcon = {
                buttonText?.let{
                    TextButton(onClick = { onClick() }) {
                        Text(
                            text = it,
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
        Surface{
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
        Surface{
            OutlinedTextFieldComponent(
                value = "Hello",
                onValueChange = {},
                label = R.string.medicine_name,
                isRequired = true,
                buttonText = "save"
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun MedicineReminderTextFieldWithIconActionPreview() {
    MedicineReminderTheme {
        Surface{
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
        Surface{
            OutlinedTextFieldComponent(
                value = "Hello",
                onValueChange = {},
                label = R.string.medicine_name,
                isRequired = true,
                isError = true,
                buttonText = "save"
            )
        }
    }
}
