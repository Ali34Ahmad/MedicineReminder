package com.example.medicinereminder.common.components.list_items

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicinereminder.common.ext.extension.toOrdered
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun DoseTimeItem(
    modifier: Modifier = Modifier,
    order: Int = 1,
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
                shape = RoundedCornerShape(20)
            )
    ){
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
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        fontWeight = FontWeight.W400
                    )
                )

                VerticalDivider(modifier = Modifier
                    .height(16.dp)
                    .padding(horizontal = 6.dp))

                Text(
                    text = time,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.W400
                    )
                )

            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$numberOfDoses $form",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.W400
                )
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