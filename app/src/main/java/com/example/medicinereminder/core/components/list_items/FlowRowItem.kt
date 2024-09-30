package com.example.medicinereminder.core.components.list_items

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun FlowRowItem(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = modifier
            .height(32.dp),
        selected = selected,
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick() },
        label = {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                )
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
            selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    )
}

@Preview(showBackground = true)
@Composable
fun FlowRowItemPreview() {
    MedicineReminderTheme {
        FlowRowItem(
            label = "Capsule",
            onClick = {},
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SelectedFlowRowItemPreview() {
    MedicineReminderTheme {
        FlowRowItem(
            label = "Capsule",
            onClick = {},
            selected = true
        )
    }
}