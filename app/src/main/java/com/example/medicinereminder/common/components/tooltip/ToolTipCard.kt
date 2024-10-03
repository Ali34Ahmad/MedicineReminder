package com.example.medicinereminder.common.components.tooltip

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
fun ToolTipCard(
    text: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    @DrawableRes trailingIcon: Int? = null,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        ),
        onClick=onClick,
    ) {
        Row(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium16)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = text,
                modifier = Modifier
                    .weight(1f),
                style = MaterialTheme.typography.bodyMedium,
            )
            trailingIcon?.let {
                IconButton(
                    onClick = {
                        onClick()
                    },
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(trailingIcon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.outline,
                    )
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TooltipPreview() {
    MedicineReminderTheme {
        Surface {
            ToolTipCard(
                modifier = Modifier.padding(
                    MaterialTheme.spacing.medium16,
                ),
                onClick = {},
                icon = Icons.Outlined.Bulb,
                text = stringResource(id = R.string.almost_finished_msg)
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TooltipWithTrailingIconPreview() {
    MedicineReminderTheme {
        Surface {
            ToolTipCard(
                modifier = Modifier.padding(
                    MaterialTheme.spacing.medium16,
                ),
                onClick = {},
                icon = Icons.Outlined.Bulb,
                trailingIcon = Icons.Outlined.ArrowRight,
                text = stringResource(id = R.string.restock_msg)
            )
        }
    }
}

