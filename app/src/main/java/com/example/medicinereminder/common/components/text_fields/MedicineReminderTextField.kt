package com.example.medicinereminder.common.components.text_fields

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun MedicineReminderTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
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
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
        ),
        label = {
            Text(text = if (isRequired) label.plus(" *") else label)
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
                        painter = painterResource(id = it),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun MedicineReminderTextFieldPreview() {
    MedicineReminderTheme {
        MedicineReminderTextField(
            value = "Hello",
            onValueChange = {},
            label = "Medicine Name",
            isRequired = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MedicineReminderTextFieldWithTextActionPreview() {
    MedicineReminderTheme {
        MedicineReminderTextField(
            value = "Hello",
            onValueChange = {},
            label = "Medicine Name",
            isRequired = true,
            buttonText = "save"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MedicineReminderTextFieldWithIconActionPreview() {
    MedicineReminderTheme {
        MedicineReminderTextField(
            value = "Hello",
            onValueChange = {},
            label = "Medicine Name",
            isRequired = true,
            buttonIcon = R.drawable.baseline_expand_more_24,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MedicineReminderTextFieldErrorPreview() {
    MedicineReminderTheme {
        MedicineReminderTextField(
            value = "Hello",
            onValueChange = {},
            label = "Medicine Name",
            isRequired = true,
            isError = true,
            buttonText = "save"
        )
    }
}
