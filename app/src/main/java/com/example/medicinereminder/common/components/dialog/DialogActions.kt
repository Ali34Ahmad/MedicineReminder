package com.example.medicinereminder.common.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.presentation.ui.theme.spacing


@Composable
fun DialogActions(
    dismissButtonText: Int,
    confirmButtonText: Int,
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.spacing.medium24),
        horizontalArrangement = Arrangement.End,
    ) {
        TextButton(onClick = onDismissRequest) {
            Text(
                text = stringResource(id = dismissButtonText),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        TextButton(onClick = onConfirmClick) {
            Text(
                text = stringResource(id = confirmButtonText),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}