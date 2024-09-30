package com.example.medicinereminder.common.components.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.presentation.ui.theme.spacing


@Composable
fun DialogHeader(
    @StringRes title: Int,
    @StringRes description: Int?,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium16),
        )
        description?.let {
            Text(
                text = stringResource(id = description),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium16),
            )
        }
    }
}