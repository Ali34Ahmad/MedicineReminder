package com.example.medicinereminder.common.components.list_item

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.common.utility.extension.toOrdered
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.additionalShapes
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun DoseTimeItem(
    modifier: Modifier = Modifier,
    order: Int,
    time: String,
    numberOfDoses: Int,
    form: String
) {
    Box(
        modifier = modifier
            .size(width = 108.dp, height = 56.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(MaterialTheme.additionalShapes.medium20)
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = order.toOrdered(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                )

                VerticalDivider(
                    modifier = Modifier
                        .height(MaterialTheme.sizing.small16)
                        .padding(horizontal = MaterialTheme.spacing.small6),
                )

                Text(
                    text = time,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small4))
            Text(
                text = "$numberOfDoses $form",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DoseTimeItemPreview() {
    MedicineReminderTheme {
        DoseTimeItem(
            order = 1,
            time = "12:00 AM",
            numberOfDoses = 3,
            form = "Pills"
        )
    }
}