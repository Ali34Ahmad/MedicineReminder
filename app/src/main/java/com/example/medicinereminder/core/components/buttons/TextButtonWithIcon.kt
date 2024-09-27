package com.example.medicinereminder.core.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.ui.theme.MedicineReminderTheme

@Composable
fun TextButtonWithIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    @DrawableRes icon: Int,
) {
    TextButton(
        onClick = { onClick() },
        modifier = modifier
    )
    {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextButtonWithIcon1Preview() {
    MedicineReminderTheme {
        TextButtonWithIcon(
            onClick = {},
            text = "Show all",
            icon = R.drawable.baseline_expand_more_24,
        )
    }
}