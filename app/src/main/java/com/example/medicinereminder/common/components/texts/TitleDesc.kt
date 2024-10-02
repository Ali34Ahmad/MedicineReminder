package com.example.medicinereminder.common.components.texts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TitleDesc(
    modifier: Modifier = Modifier,
    title:String,
    subtitle:String,
    isWarning: Boolean = false
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 16.sp
            )
        )
        val color = if (isWarning)
                            MaterialTheme.colorScheme.error
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
        Text(
            text = subtitle,
            style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = color
            )
            )
    }
}

@Preview( showBackground = true, showSystemUi = false)
@Composable
fun TitleDescPreview() {
    MaterialTheme {
        TitleDesc(title = "Vitamin D", subtitle = "1 Pill" )
    }
}

@Preview( showBackground = true, showSystemUi = false)
@Composable
fun TitleDescWarningPreview() {
    MaterialTheme {
        TitleDesc(title = "Vitamin D", subtitle = "Out of stock", isWarning = true )
    }
}