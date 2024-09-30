package com.example.medicinereminder.core.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun ToolTipWithAction(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    medicineId: Int
    ) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .height(80.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 22.dp)
                    .size(18.dp),

            )
            Text(
                stringResource(R.string.restock_msg),
                modifier = Modifier
                    .width(222.dp)
                    .height(40.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                ),
            )
            IconButton(
                onClick = {
                    onClick(medicineId)
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = stringResource(R.string.go_to_refill_msg),
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true,)
@Composable
fun TooltipWithActionPreview() {
    MedicineReminderTheme {
        Surface {
            ToolTipWithAction(onClick ={}, medicineId = 0)
        }
    }
}