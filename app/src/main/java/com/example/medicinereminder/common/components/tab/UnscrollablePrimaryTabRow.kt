package com.example.medicinereminder.common.components.tab

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.TabItems
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnscrollablePrimaryTabRow(
    selectedTabIndex: Int,
    itemsStringRes: List<Int>,
    onItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    PrimaryTabRow(selectedTabIndex = selectedTabIndex) {
        itemsStringRes.forEachIndexed { index, text ->
            TabItem(
                text = stringResource(id = text),
                selected = index == selectedTabIndex,
                index = index,
                onClick = onItemClick,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TabRowPreview() {
    MedicineReminderTheme {
        Surface {
            UnscrollablePrimaryTabRow(
                itemsStringRes = TabItems.medicinesScreen(),
                onItemClick = {},
                selectedTabIndex = 1,
            )
        }
    }
}