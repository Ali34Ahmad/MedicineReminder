package com.example.medicinereminder.common.components.texts

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun HeadingText(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    isOptional: Boolean
) {
    val trailingText = if(isOptional) " " + stringResource(id = R.string.optional) else ""
    val text = stringResource(id = title) + trailingText
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleSmall,
    )
}

@Preview(showBackground = true)
@Composable
fun HeadingTextPreview() {
    MedicineReminderTheme {
        HeadingText(
            title = R.string.contact_info,
            isOptional = true
        )
    }
}