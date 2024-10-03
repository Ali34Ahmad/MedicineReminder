package com.example.medicinereminder.common.components.list_item

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun ClickableIconAndText(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String,
    desc: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(8)
                )
        ){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(4.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500
                )
            )
            Text(
                text = desc,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClickableIconAndTextPreview() {
    MedicineReminderTheme {
        ClickableIconAndText(
            icon = R.drawable.ic_launcher_foreground,
            title = "title",
            desc = "the description is here"
        )
    }
}