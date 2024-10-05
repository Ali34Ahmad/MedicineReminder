package com.example.medicinereminder.common.components.list_item

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.texts.TitleAndSubtitle
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun MedicineItem(
    modifier: Modifier = Modifier,
    isOutOfStock: Boolean,
    @DrawableRes warningIcon: Int? = null,
    onClick:() -> Unit,
    @DrawableRes defaultImage: Int,
    medicineName: String,
    currentAmount:Int,
    @StringRes form: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(
                horizontal = MaterialTheme.spacing.medium16,
                vertical = MaterialTheme.spacing.medium12
                ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        val suffix = if(currentAmount>1) "s" else ""
        val subtitle = if(isOutOfStock) stringResource(id = R.string.out_of_stock)
        else "$currentAmount ${stringResource(id = form)}$suffix"
        Image(
            modifier = Modifier
                .size(
                    MaterialTheme.sizing.large52
                )
                .clip(shape = CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainerLow) ,
            painter = painterResource(id = defaultImage),
            contentDescription = null,
        )

        TitleAndSubtitle(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = MaterialTheme.spacing.medium16,
                ),
            title = medicineName ,
            subtitle = subtitle,
            isWarning = isOutOfStock
        )
        warningIcon?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedicineItemPreview() {
    MedicineReminderTheme {
        MedicineItem(
            isOutOfStock = false,
            defaultImage = R.drawable.doctor_default_image,
            onClick = {},
            medicineName = "Vitamin C",
            currentAmount = 2,
            form = R.string.pill
        )
    }
}
@Preview(showBackground = true)
@Composable
fun OutOfStockMedicineItemPreview() {
    MedicineReminderTheme {
        MedicineItem(
            isOutOfStock = true,
            warningIcon = R.drawable.ic_error_outlined,
            defaultImage = R.drawable.doctor_default_image,
            onClick = {},
            medicineName = "Vitamin C",
            currentAmount = 1,
            form = R.string.pill
            )
    }
}