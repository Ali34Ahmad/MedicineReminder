package com.example.medicinereminder.common.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun ButtonRow(
    @StringRes primaryButtonText: Int,
    @StringRes secondaryButtonText: Int,
    onPrimaryButtonClick: () -> Unit,
    onSecondaryButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isPrimaryButtonEnabled: Boolean = true
) {
    Box(modifier=modifier){
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(
                    horizontal = MaterialTheme.spacing.medium16,
                    vertical = MaterialTheme.spacing.small8
                ),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
        ) {
            OutlinedButton(
                onClick = onSecondaryButtonClick,
                modifier = Modifier.weight(1f),
            ) {
                Text(text = stringResource(secondaryButtonText))
            }
            Button(
                onClick = onPrimaryButtonClick,
                modifier = Modifier.weight(1f),
                enabled = isPrimaryButtonEnabled
            ) {
                Text(
                    text = stringResource(primaryButtonText),
                )
            }
        }
    }
}


@DarkAndLightModePreview
@Composable
fun ButtonRowPreview() {
    MedicineReminderTheme {
        Surface {
            ButtonRow(
                primaryButtonText = R.string.cancel,
                secondaryButtonText = R.string.confirm,
                onPrimaryButtonClick = { },
                onSecondaryButtonClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}