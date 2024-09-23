package com.example.medicinereminder.common.components.top_app_bar

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
import androidx.compose.ui.text.font.FontWeight
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun TopAppBarWithCounterAndIcons(
    counter: Int,
    onCancelClick: () -> Unit,
    onDeleteIconClick: () -> Unit,
    onEditIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBarWrapper(
        hasLeadingIcon = true,
        modifier = modifier
    ) {
        IconButton(onClick = onCancelClick) {
            Icon(
                imageVector = ImageVector.vectorResource(id = Icons.Outlined.Close),
                contentDescription = stringResource(id = R.string.cancel),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Text(
            text = counter.toString(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = MaterialTheme.spacing.small8),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
        )
        IconButton(onClick = onDeleteIconClick) {
            Icon(
                imageVector = ImageVector.vectorResource(Icons.Outlined.Delete),
                contentDescription = stringResource(id = R.string.delete),
                tint = MaterialTheme.colorScheme.error,
            )
        }
        if (counter>1) {
            IconButton(onClick = onEditIconClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(Icons.Outlined.Edit),
                    contentDescription = stringResource(id = R.string.more_options),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TopAppBarWithCounterEqualsOneAndIconsPreview(){
    MedicineReminderTheme{
        Surface{
            TopAppBarWithCounterAndIcons(
                counter = 1,
                onDeleteIconClick = {},
                onEditIconClick = {},
                onCancelClick = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TopAppBarWithCounterBiggerThanOneAndIconsPreview(){
    MedicineReminderTheme{
        Surface{
            TopAppBarWithCounterAndIcons(
                counter = 2,
                onDeleteIconClick = {},
                onEditIconClick = {},
                onCancelClick = {},
            )
        }
    }
}

