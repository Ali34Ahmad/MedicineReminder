package com.example.medicinereminder.common.components.list_item

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun ListItemWithSwitch(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    subTitle: String? = null,
    checked: Boolean,
    showLongDescription: Boolean = false,
    onCheckedChange: (Boolean) -> Unit
) {
    val verticalAlignment =
        if (subTitle.isNullOrBlank())
            Alignment.CenterVertically
        else
            Alignment.Top
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium16,
                    vertical = MaterialTheme.spacing.small8
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = verticalAlignment,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        vertical = MaterialTheme.spacing.small8
                    ),
            ) {
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.medium16),
                )
                subTitle?.let {
                    Text(
                        text = subTitle,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = if (!showLongDescription) 1 else Int.MAX_VALUE,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = MaterialTheme.spacing.small8),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    uncheckedThumbColor = MaterialTheme.colorScheme.surfaceVariant,
                    checkedThumbColor = MaterialTheme.colorScheme.secondary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.outlineVariant,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun SwitchComponentOffPreview() {
    var checked by remember {
        mutableStateOf(false)
    }
    MedicineReminderTheme {
        Surface {
            ListItemWithSwitch(
                title = R.string.night_mode,
                subTitle = if (checked) "On" else "Off",
                onCheckedChange = {
                    checked = it
                },
                checked = checked,
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun SwitchComponentOnPreview() {
    var checked by remember {
        mutableStateOf(true)
    }
    MedicineReminderTheme {
        Surface {
            ListItemWithSwitch(
                title = R.string.night_mode,
                subTitle = if (checked) "On" else "Off",
                onCheckedChange = {
                    checked = it
                },
                checked = checked
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun SwitchComponentOffNotDescriptionPreview() {
    var checked by remember {
        mutableStateOf(false)
    }
    MedicineReminderTheme {
        Surface {
            ListItemWithSwitch(
                title = R.string.night_mode,
                onCheckedChange = {
                    checked = it
                },
                checked = checked
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun SwitchComponentWithLongDescriptionDisabledPreview() {
    var checked by remember {
        mutableStateOf(true)
    }
    MedicineReminderTheme {
        Surface {
            ListItemWithSwitch(
                title = R.string.night_mode,
                subTitle = if (checked) "If your dosage varies, uncheck this option and specify details below." else "Off",
                onCheckedChange = {
                    checked = it
                },
                checked = checked,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun SwitchComponentWithLongDescriptionEnabledPreview() {
    var checked by remember {
        mutableStateOf(true)
    }
    MedicineReminderTheme {
        Surface {
            ListItemWithSwitch(
                title = R.string.night_mode,
                subTitle = if (checked) "If your dosage varies, uncheck this option and specify details below." else "Off",
                onCheckedChange = {
                    checked = it
                },
                checked = checked,
                showLongDescription = true,
            )
        }
    }
}

