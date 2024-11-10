package com.example.medicinereminder.common.components.time_picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.medicinereminder.common.enums.DayPeriod
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing
import kotlinx.coroutines.CoroutineScope

@Composable
fun DayPeriodList(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        VerticalDivider(
            modifier = Modifier.height(MaterialTheme.sizing.large32)
        )
        LazyColumn(
            modifier = modifier
                .height(MaterialTheme.sizing.large132)
                .width(MaterialTheme.sizing.large52),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium18),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = MaterialTheme.spacing.small6)
        ) {
            items(DayPeriod.entries) {
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
        VerticalDivider(
            modifier = Modifier.height(MaterialTheme.sizing.large32),
        )
    }
}

@DarkAndLightModePreview
@Composable
fun DayPeriodListPreview() {
    MedicineReminderTheme {
        Surface {
            DayPeriodList()
        }
    }
}