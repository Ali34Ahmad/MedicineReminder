package com.example.medicinereminder.common.components.top_app_bar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun TopAppBarWithTitleAndTextButton(
    hasLeadingIcon: Boolean,
    title: String,
    trailingText: String,
    onNavigateUpClick: () -> Unit,
    onTrailingTextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBarWrapper(
        hasLeadingIcon = hasLeadingIcon,
        modifier = modifier
    ) {
        if (hasLeadingIcon) {
            IconButton(onClick = onNavigateUpClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(Icons.Outlined.ArrowBack),
                    contentDescription = stringResource(id = R.string.go_back),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = MaterialTheme.spacing.small8),
        )
        TextButton(onClick = onTrailingTextClick) {
            Text(
                text = trailingText,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TopAppBarWithTitleAndTrailingTextPreview() {
    MedicineReminderTheme {
        Surface {
            TopAppBarWithTitleAndTextButton(
                onNavigateUpClick = {},
                title = stringResource(id = R.string.add_medicine),
                hasLeadingIcon = true,
                onTrailingTextClick = {},
                trailingText = stringResource(id = R.string.skip),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TopAppBarWithTitleAndTrailingTextAndNavigateUpPreview() {
    MedicineReminderTheme {
        Surface {
            TopAppBarWithTitleAndTextButton(
                onNavigateUpClick = {},
                title = stringResource(id = R.string.add_medicine),
                hasLeadingIcon = true,
                onTrailingTextClick = {},
                trailingText = stringResource(id = R.string.skip)
            )
        }
    }
}