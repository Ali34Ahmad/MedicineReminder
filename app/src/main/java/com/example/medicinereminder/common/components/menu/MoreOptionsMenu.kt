package com.example.medicinereminder.common.components.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.constants.MenuItems
import com.example.medicinereminder.presentation.ui.model.MoreOptionMenuItem
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun MoreOptionsMenu(
    menuItems: List<MoreOptionMenuItem>,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
            .width(180.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        menuItems.forEachIndexed { index, item ->
            MoreOptionItem(
                menuItem = item,
                onItemClick = onItemClick,
                index = index,
            )
        }
    }
}

@Composable
fun MoreOptionItem(
    menuItem: MoreOptionMenuItem,
    onItemClick: (index: Int) -> Unit,
    index: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(index)
            }
            .padding(
                horizontal = MaterialTheme.spacing.small4,
                vertical = MaterialTheme.spacing.medium16
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(menuItem.icon),
                contentDescription = null,
                modifier = Modifier.padding(start = MaterialTheme.spacing.small4),
                tint = menuItem.tint,
            )
            Text(
                text = stringResource(menuItem.titleStringId),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = MaterialTheme.spacing.medium16),
                color = menuItem.textColor,
            )
            if (menuItem.hasSubMenu) {
                Icon(
                    imageVector = ImageVector.vectorResource(Icons.Outlined.ArrowRightUp),
                    contentDescription = null,
                    modifier = Modifier.padding(end = MaterialTheme.spacing.small4),
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun MoreOptionsMenuPreview() {
    MedicineReminderTheme {
        Surface {
            MoreOptionsMenu(
                expanded = true,
                onDismissRequest = {},
                onItemClick = {},
                menuItems = MenuItems.medicineMenuItems()
            )
        }
    }
}