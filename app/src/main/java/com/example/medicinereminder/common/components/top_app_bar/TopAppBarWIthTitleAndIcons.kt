package com.example.medicinereminder.common.components.top_app_bar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
fun StandardTopAppBarComponent(
    showTrailingIcon: Boolean,
    showNavigateUp: Boolean,
    modifier: Modifier = Modifier,
    @StringRes title: Int?=null,
    onNavigateUpClick: () -> Unit={},
    onTrailingIconClick: () -> Unit={},
    @DrawableRes trailingIcon: Int? = null
) {
    TopAppBarWrapper(
        hasLeadingIcon = showNavigateUp,
        modifier = modifier
    ) {
        if (showNavigateUp) {
            IconButton(onClick = onNavigateUpClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(Icons.Outlined.ArrowBack),
                    contentDescription = stringResource(id = R.string.go_back),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
        title?.let{
            Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = MaterialTheme.spacing.small8),
        )
    }
        if (showTrailingIcon) {
            IconButton(onClick = onTrailingIconClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(Icons.Outlined.More),
                    contentDescription = stringResource(id = R.string.more_options),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TopAppBarWithNavigateUpButtonWithoutTitlePreview() {
    MedicineReminderTheme {
        Surface {
            StandardTopAppBarComponent(
                showTrailingIcon = true,
                onNavigateUpClick = {},
                onTrailingIconClick = {},
                showNavigateUp = true
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TopAppBarWithNavigateUpButtonWithoutTrailingIconPreview() {
    MedicineReminderTheme {
        Surface {
            StandardTopAppBarComponent(
                showTrailingIcon = false,
                onNavigateUpClick = {},
                title = R.string.add_medicine,
                onTrailingIconClick = {},
                showNavigateUp = true
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TopAppBarWithNavigateUpButtonWithTitleAndTrailingIconPreview() {
    MedicineReminderTheme {
        Surface {
            StandardTopAppBarComponent(
                showTrailingIcon = true,
                onNavigateUpClick = {},
                title = R.string.add_medicine,
                onTrailingIconClick = {},
                showNavigateUp = true
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TopAppBarWithTitleOnlyPreview() {
    MedicineReminderTheme {
        Surface {
            StandardTopAppBarComponent(
                showTrailingIcon = false,
                onNavigateUpClick = {},
                title = R.string.app_name,
                onTrailingIconClick = {},
                showNavigateUp = false,
            )
        }
    }
}

