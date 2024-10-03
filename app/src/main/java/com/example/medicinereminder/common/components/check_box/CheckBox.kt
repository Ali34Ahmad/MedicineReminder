package com.example.medicinereminder.common.components.check_box

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.style.TextOverflow
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun CheckBoxComponent(
    checked: Boolean,
    text: String,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.default),
    textPadding:PaddingValues = PaddingValues(start=MaterialTheme.spacing.medium16),
) {
    Box(modifier = modifier
        //.indication(indication = ri)
        .clickable {
            onCheckedChange(!checked)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(contentPadding)
        ) {
            Checkbox(checked = checked, onCheckedChange = onCheckedChange)
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(textPadding),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun CheckBoxComponentPreview() {
    var checked by remember {
        mutableStateOf(false)
    }
    MedicineReminderTheme {
        Surface {
            CheckBoxComponent(
                checked = checked,
                text = "Avoid tea or milk within 3 hours of taking this medication.",
                onCheckedChange = { checked = it }
            )
        }
    }
}