package com.example.medicinereminder.common.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun FooterButtons(
    modifier: Modifier = Modifier,
    onLeftButtonClick: () ->Unit,
    onRightButtonClick: () -> Unit,
    @StringRes leftButtonText: Int,
    @StringRes rightButtonText: Int,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(
                MaterialTheme.spacing.medium16
            )
            .fillMaxWidth()
    ) {
        OutlinedButton(
            onClick = onLeftButtonClick,
            border = BorderStroke(
                width = MaterialTheme.spacing.small1,
                color = MaterialTheme.colorScheme.error
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text(
                text = stringResource(id = leftButtonText),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Spacer(
            modifier = Modifier.width(
            MaterialTheme.spacing.medium16
            )
        )
        Button(
            onClick = onRightButtonClick,
        ) {
            Text(
                text = stringResource(id = rightButtonText),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FooterButtonsPreview() {
    MedicineReminderTheme {
        FooterButtons(
            onLeftButtonClick = {},
            onRightButtonClick = { },
            leftButtonText = R.string.delete_medicine,
            rightButtonText = R.string.view_medicine
        )
    }
}