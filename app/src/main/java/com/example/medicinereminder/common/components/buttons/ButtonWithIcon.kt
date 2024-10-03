package com.example.medicinereminder.common.components.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun ButtonWithIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes text: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyLarge,
        )
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.small8),
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@DarkAndLightModePreview
@Composable
fun ButtonWithIconPreview() {
    MedicineReminderTheme {
        Surface {
            ButtonWithIcon(
                icon = Icons.Outlined.ArrowDropDown,
                text = R.string.mark_as,
                onClick = {}
            )
        }
    }
}