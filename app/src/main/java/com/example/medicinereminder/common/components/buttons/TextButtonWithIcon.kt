package com.example.medicinereminder.common.components.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun TextButtonWithIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @StringRes text: Int,
    @DrawableRes icon: Int,
) {
    TextButton(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.small8),
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TextButtonWithIcon1Preview() {
    MedicineReminderTheme {
        Surface {
            TextButtonWithIcon(
                onClick = {},
                text = R.string.show_more,
                icon = Icons.Outlined.Expand,
            )
        }
    }
}

