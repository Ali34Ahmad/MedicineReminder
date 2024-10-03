package com.example.medicinereminder.common.components.chip

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.common.ext.toShortName
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.ChipState
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing
import java.time.DayOfWeek

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InputChipWithThreeStates(
    text: String,
    onClick: (index: Int) -> Unit,
    chipState: ChipState,
    clickable: Boolean,
    index: Int,
    longClickable: Boolean,
    onLongClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (clickable && chipState == ChipState.DISABLED) throw Exception(stringResource(id = R.string.chip_error_clickable_disabled))
    if (longClickable && chipState == ChipState.DISABLED) throw Exception(stringResource(id = R.string.chip_error_long_lickable_disabled))
    val haptics = LocalHapticFeedback.current
    val enabled by rememberSaveable {
        mutableStateOf(chipState != ChipState.DISABLED)
    }

    val (backgroundStyle,rowModifier, textColor) = Modifier.getChipStyle(chipState)
    val boxBackgroundModifier: Modifier =
        if (chipState != ChipState.DISABLED)
            backgroundStyle
                .combinedClickable(
                    onLongClick = {
                        if (longClickable && enabled) {
                            onLongClick(index)
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                        }
                    },
                    onClick = { if (clickable && enabled) onClick(index) }
                )
        else
            backgroundStyle

    Box(
        modifier = Modifier
            .then(boxBackgroundModifier)
    ) {
        Row(
            modifier = rowModifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (chipState == ChipState.SELECTED_WITH_ICON)
                Icon(
                    imageVector = Icons.Outlined.Check, contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(
                    start = if (chipState == ChipState.SELECTED_WITH_ICON)
                        MaterialTheme.spacing.small8
                    else MaterialTheme.spacing.default,
                ),
                color = textColor
            )
        }
    }
}

typealias ContainerNonClickableModifier = Modifier
typealias RowPaddingModifier = Modifier
typealias TextColor = Color

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Modifier.getChipStyle(chipState: ChipState): Triple<ContainerNonClickableModifier, RowPaddingModifier, TextColor> {
    val rowPaddingModifier = if (chipState == ChipState.SELECTED_WITH_ICON)
        Modifier.padding(
            start = MaterialTheme.spacing.small8,
            end = MaterialTheme.spacing.medium16,
            top = MaterialTheme.spacing.small6,
            bottom = MaterialTheme.spacing.small6,
        )
    else
        Modifier.padding(
            start = MaterialTheme.spacing.medium16,
            end = MaterialTheme.spacing.medium16,
            top = MaterialTheme.spacing.small6,
            bottom = MaterialTheme.spacing.small6,
        )

    return when (chipState) {
        ChipState.SELECTED, ChipState.SELECTED_WITH_ICON ->
            Triple(
                this
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = MaterialTheme.shapes.small
                    ),
                rowPaddingModifier,
                MaterialTheme.colorScheme.onSecondaryContainer,
            )

        ChipState.UNSELECTED ->
            Triple(
                this
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.small
                    )
                    .border(
                        width = 1.dp, color = MaterialTheme.colorScheme.outline,
                        shape = MaterialTheme.shapes.small
                    ),
                rowPaddingModifier,
                MaterialTheme.colorScheme.onSurfaceVariant,
            )

        else -> Triple(
            this
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.small
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.30f),
                    shape = MaterialTheme.shapes.small
                ),
            rowPaddingModifier,
            MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.38f),
        )
    }
}


@DarkAndLightModePreview
@Composable
fun SelectedChipPreview() {
    MedicineReminderTheme {
        Surface {
            InputChipWithThreeStates(
                text = DayOfWeek.MONDAY.toShortName(),
                onClick = {},
                chipState = ChipState.UNSELECTED,
                clickable = true,
                longClickable = true,
                onLongClick = {},
                index = 0,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun UnselectedChipPreview() {
    MedicineReminderTheme {
        Surface {
            InputChipWithThreeStates(
                text = DayOfWeek.MONDAY.toShortName(),
                onClick = {},
                chipState = ChipState.SELECTED,
                clickable = true,
                longClickable = true,
                onLongClick = {},
                index = 0
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun SelectedWithIconChipPreview() {
    var text by remember {
        mutableStateOf("chip")
    }

    MedicineReminderTheme {
        Surface {
            InputChipWithThreeStates(
                text = text,
                onClick = { text = "Click" },
                chipState = ChipState.SELECTED_WITH_ICON,
                clickable = true,
                longClickable = true,
                onLongClick = { text = "Long" },
                index = 0,
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun DisabledChipPreview() {
    MedicineReminderTheme {
        Surface {
            InputChipWithThreeStates(
                text = DayOfWeek.MONDAY.toShortName(),
                onClick = {},
                chipState = ChipState.DISABLED,
                clickable = false,
                longClickable = false,
                onLongClick = {},
                index = 0
            )
        }
    }
}

