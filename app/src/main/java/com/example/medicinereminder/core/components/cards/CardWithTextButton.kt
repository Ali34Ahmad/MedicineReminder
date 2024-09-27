package com.example.medicinereminder.core.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
fun CardWithTextButton(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(76.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ){
                Text(
                    title,
                    style = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    subTitle,
                    style = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

            }
            TextButton(onClick = { onClick()}) {
                Text(
                    text = buttonText,
                    style = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CardWithTextButtonPreview() {
    MedicineReminderTheme {
        CardWithTextButton(
            title = "Number of repeats",
            subTitle = "3 times",
            buttonText = "Change",
            onClick = {}
        )
    }
}