package com.example.medicinereminder.common.components.list_item

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.medicinereminder.R
import com.example.medicinereminder.data.local.pharmaceuticalForms
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun MedicineFormFlowRowItem(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    onTrailingIconClick: () -> Unit = {},
    @DrawableRes trailingIcon: Int? = null,
) {
    FilterChip(
        modifier = modifier,
        selected = selected,
        onClick = { onClick() },
        label = {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
            )
        },
        trailingIcon = {
            trailingIcon?.let {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            onTrailingIconClick()
                        }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(trailingIcon),
                        contentDescription = null,
                        modifier = Modifier.padding(MaterialTheme.spacing.small4),
                    )
                }
            }
        }
    )
}

@DarkAndLightModePreview
@Composable
fun FlowRowItemUnselectedPreview() {
    MedicineReminderTheme {
        Surface {
            MedicineFormFlowRowItem(
                text = pharmaceuticalForms[0].name,
                onClick = {},
                selected = false,
                onTrailingIconClick = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun FlowRowItemSelectedPreview() {
    MedicineReminderTheme {
        Surface {
            MedicineFormFlowRowItem(
                text = stringResource(id = R.string.add_form),
                onClick = {},
                selected = false,
                trailingIcon = Icons.Outlined.Add,
                onTrailingIconClick = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun FlowRowItemWithTrailingIconPreview() {
    MedicineReminderTheme {
        Surface {
            MedicineFormFlowRowItem(
                text = pharmaceuticalForms[0].name,
                onClick = {},
                selected = true,
                trailingIcon = Icons.Outlined.Close,
                onTrailingIconClick = {},
            )
        }
    }
}

