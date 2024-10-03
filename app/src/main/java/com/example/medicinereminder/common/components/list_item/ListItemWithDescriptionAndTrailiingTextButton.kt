package com.example.medicinereminder.common.components.list_item

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing
import com.example.medicinereminder.R

@Composable
fun ListItemWithDescriptionAndTrailingTextButton(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    subTitle: String,
    @StringRes buttonText: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium16),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.medium16),
                )
                Text(
                    text = subTitle,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.small8),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            TextButton(onClick = { onClick() }) {
                Text(
                    text = stringResource(id = buttonText),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }

}

@DarkAndLightModePreview
@Composable
fun ListItemWithDescriptionAndTrailingTextButtonPreview() {
    MedicineReminderTheme {
        Surface {
            ListItemWithDescriptionAndTrailingTextButton(
                title = R.string.number_of_repeats,
                subTitle = "3 times",
                buttonText = R.string.change,
                onClick = {},
            )
        }
    }
}
