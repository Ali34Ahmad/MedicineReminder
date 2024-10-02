package com.example.medicinereminder.common.components.texts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun TrailingTimeItem(
    modifier: Modifier = Modifier,
    time: String,
    desc: String? = null,
    trailingIcon: Int? = null
) {
    Column(
        modifier = modifier.padding(end = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End
    ){
        Text(
            text = time,
            style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        if(desc.isNullOrBlank() && trailingIcon !=null){
            Icon(
                painter = painterResource(id = trailingIcon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        else if(!desc.isNullOrBlank() && trailingIcon == null ){
            Text(
                text = desc,
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TrailerTextPreview() {
    MaterialTheme{
        TrailingTimeItem(
            time = "9:15 AM"
        )
    }
}