package com.example.medicinereminder.presentation.ui.constants

import androidx.compose.material.icons.outlined.Share
import androidx.compose.ui.graphics.Color
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.model.MoreOptionMenuItem

object MenuItems {
    fun medicineMenuItems() = listOf(
        MoreOptionMenuItem(
            icon = Icons.Outlined.Edit,
            titleStringId = R.string.edit,
            hasSubMenu = true,
        ),
        MoreOptionMenuItem(
            icon = Icons.Outlined.Switch,
            titleStringId = R.string.switch_,
            hasSubMenu = false,
        ),
        MoreOptionMenuItem(
            icon = Icons.Outlined.Delete,
            titleStringId = R.string.delete,
            hasSubMenu = false,
            tint = Color.Red,
        ),
    )
    fun medicineSubMenuItems() = listOf(
        MoreOptionMenuItem(
            icon = Icons.Outlined.Edit,
            titleStringId = R.string.all,
        ),
        MoreOptionMenuItem(
            icon = Icons.Outlined.Details,
            titleStringId = R.string.details,
        ),
        MoreOptionMenuItem(
            icon = Icons.Outlined.Clock,
            titleStringId = R.string.program,
        ),
        MoreOptionMenuItem(
            icon = Icons.Outlined.Image,
            titleStringId = R.string.photo,
        ),
    )
    fun doctorMenuItems() = listOf(
        MoreOptionMenuItem(
            icon = Icons.Outlined.Edit,
            titleStringId = R.string.edit,
            hasSubMenu = true,
        ),
        MoreOptionMenuItem(
            icon = Icons.Outlined.Delete,
            titleStringId = R.string.delete,
            hasSubMenu = false,
            tint = Color.Red,
        ),
    )
    fun doctorSubMenuItems() = listOf(
        MoreOptionMenuItem(
            icon = Icons.Outlined.Edit,
            titleStringId = R.string.all,
        ),
        MoreOptionMenuItem(
            icon = Icons.Outlined.Delete,
            titleStringId = R.string.delete,
        ),
        MoreOptionMenuItem(
            icon = Icons.Outlined.Image,
            titleStringId = R.string.photo,
        ),
    )
    fun markMedicineAsMenuItem() = listOf(
        MoreOptionMenuItem(
            icon = Icons.Outlined.Check,
            titleStringId = R.string.completed,
        ),
        MoreOptionMenuItem(
            icon = Icons.Outlined.NotificationMissed,
            titleStringId = R.string.missed,
        ),
    )

    fun durationUnits() = listOf(
        MoreOptionMenuItem(
            titleStringId = R.string.hour,
        ),
        MoreOptionMenuItem(
            titleStringId = R.string.day,
        ),
        MoreOptionMenuItem(
            titleStringId = R.string.week,
        ),
        MoreOptionMenuItem(
            titleStringId = R.string.month,
        ),
        MoreOptionMenuItem(
            titleStringId = R.string.year,
        ),
    )


}
