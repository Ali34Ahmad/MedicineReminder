package com.example.medicinereminder.common.components.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.TabItems
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun ScrollableTab(
    itemsStringRes: List<Int>,
    showBadges: Boolean,
    selectedItemIndex: Int,
    onTabClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    badges: List<Int> = emptyList(),
) {
    if (showBadges && badges.isEmpty()) throw Exception(stringResource(id = R.string.tab_composable_exception_no_badges))
    if (showBadges && itemsStringRes.size != badges.size) throw Exception(stringResource(id = R.string.tab_composable_exception_badges_items_incompitable))
    val scrollableState = rememberScrollState()

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .scrollable(
                orientation = Orientation.Horizontal,
                state = scrollableState
            ),
    ) {
        items(itemsStringRes.size) { index ->
            CustomTabItem(
                textResId = itemsStringRes[index],
                onTabClick = onTabClick,
                index = index,
                selected = selectedItemIndex==index,
                showBadges = showBadges,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun ScrollableTabPreview() {
    MedicineReminderTheme {
        Surface {
            ScrollableTab(
                itemsStringRes = TabItems.homeScreen(),
                selectedItemIndex = 0,
                showBadges = false,
                onTabClick = {},
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun ScrollableTabWithBadgesPreview() {
    MedicineReminderTheme {
        Surface {
            ScrollableTab(
                itemsStringRes = TabItems.homeScreen(),
                selectedItemIndex = 0,
                showBadges = true,
                badges = listOf(6, 1, 3, 2),
                onTabClick = {},
            )
        }
    }
}

