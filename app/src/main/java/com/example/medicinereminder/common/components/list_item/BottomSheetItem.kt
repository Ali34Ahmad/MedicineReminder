package com.example.medicinereminder.common.components.list_item

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.model.BottomSheetInfo
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun BottomSheetItem(
    modifier: Modifier = Modifier,
    data: BottomSheetInfo
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ){
        Icon(
            painter = painterResource(id = data.icon),
            contentDescription = null,
            modifier = Modifier.padding(
                MaterialTheme.spacing.medium16
            )
        )
        Text(
            text = stringResource(id = data.text),
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = MaterialTheme.colorScheme.onSurface,

            ),
            modifier = Modifier.padding(
                vertical = MaterialTheme.spacing.medium16
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetItemPreview() {
    MedicineReminderTheme {
        BottomSheetItem(
            data = BottomSheetInfo(
                icon = R.drawable.ic_check,
                text = R.string.mark_as_taken,
                onClick = {}
            )
        )
    }
}