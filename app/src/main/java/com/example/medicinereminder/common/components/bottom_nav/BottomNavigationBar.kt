package com.example.medicinereminder.common.components.bottom_nav

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.BottomNavItems
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    onNavItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) {
        BottomNavItems.get().forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onNavItemClick(index) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = getIcon(
                                selectedIndex,
                                index
                            )
                        ),
                        contentDescription = null,
                        tint = if (selectedIndex == index) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.label),
                        color =
                        if (selectedIndex == index)
                            MaterialTheme.colorScheme.onSurface
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )
        }
    }
}

private fun getIcon(selectedIndex: Int, index: Int): Int {
    return if (selectedIndex == index)
        getFilledIconByIndex(index)
    else
        getOutlinedIconByIndex(index)
}

private fun getOutlinedIconByIndex(index: Int): Int {
    return when (index) {
        0 -> Icons.Outlined.TodaySchedule
        1 -> Icons.Outlined.Medicine
        2 -> Icons.Outlined.Appointment
        3 -> Icons.Outlined.Tracker
        else -> Icons.Outlined.Medicine
    }
}

fun getFilledIconByIndex(index: Int): Int {
    return when (index) {
        0 -> Icons.Filled.TodaySchedule
        1 -> Icons.Filled.Medicine
        2 -> Icons.Filled.Appointment
        3 -> Icons.Filled.Tracker
        else -> Icons.Filled.Medicine
    }
}

@DarkAndLightModePreview
@Composable
fun BottomNavigationBarPreview() {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    MedicineReminderTheme {
        Surface {
            BottomNavigationBar(
                selectedIndex = selectedIndex,
                onNavItemClick = {
                    selectedIndex = it
                }
            )
        }
    }
}