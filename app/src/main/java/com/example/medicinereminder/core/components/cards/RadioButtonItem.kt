package com.example.medicinereminder.core.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicinereminder.ui.theme.MedicineReminderTheme

@Composable
fun RadioButtonCard(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RectangleShape,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                modifier = Modifier.size(48.dp),
                selected = selected ,
                onClick = { onClick() }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RadioButtonCardOnPreview() {
    MedicineReminderTheme {
        RadioButtonCard(
            text = "English",
            onClick = {},
            selected = true
        )
    }
}
@Preview(showBackground = true)
@Composable
fun RadioButtonCardOffPreview() {
    MedicineReminderTheme {
        RadioButtonCard(
            text = "English",
            onClick = {},
        )
    }
}