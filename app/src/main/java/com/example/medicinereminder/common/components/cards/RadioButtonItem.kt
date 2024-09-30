package com.example.medicinereminder.common.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun RadioButtonItem(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    style:TextStyle = MaterialTheme.typography.bodyLarge,
    contentPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.default),
    textPadding:PaddingValues = PaddingValues(start=MaterialTheme.spacing.medium16),
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(contentPadding),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selected,
                onClick = { onClick() }
            )
            Text(
                text = text,
                style = style,
                modifier = Modifier.padding(textPadding),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun RadioButtonCardOnPreview() {
    var selected by remember {
        mutableStateOf(false)
    }
    MedicineReminderTheme {
        Surface {
            RadioButtonItem(
                text = "English",
                onClick = {
                    selected = true
                },
                selected = selected
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun RadioButtonCardOffPreview() {
    MedicineReminderTheme {
        Surface {
            RadioButtonItem(
                text = "English",
                onClick = {},
                selected = true,
            )
        }
    }
}