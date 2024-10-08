package com.example.medicinereminder.common.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.additionalShapes
import com.example.medicinereminder.presentation.ui.theme.sizing

@Composable
fun OutlinedIconButtonComponent(
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = MaterialTheme.colorScheme.outlineVariant,
) {
    OutlinedIconButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(
            width = MaterialTheme.sizing.small1,
            color = borderColor,
        ),
        modifier = modifier,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = null,
        )
    }
}

@DarkAndLightModePreview
@Composable
fun OutlinedIconButtonPreview() {
    MedicineReminderTheme {
        Surface {
            OutlinedIconButtonComponent(
                icon = Icons.Outlined.Phone,
                onClick = {},
            )
        }
    }
}