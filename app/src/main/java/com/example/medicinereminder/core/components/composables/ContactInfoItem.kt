package com.example.medicinereminder.core.components.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.medicinereminder.R
import com.example.medicinereminder.ui.theme.MedicineReminderTheme

@Composable
fun ContactInfoItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        TextButton(onClick = { onClick() }) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactInfoItemPreview() {
    MedicineReminderTheme {
       ContactInfoItem(
           icon = R.drawable.baseline_location_pin_24,
           text = "Lattakia _ Alzera’ah _ Al-Awokaf Street",
           onClick = {}
       )
    }
}
