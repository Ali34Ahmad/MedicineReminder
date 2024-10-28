package com.example.medicinereminder.common.components.time_picker

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing
import kotlinx.coroutines.CoroutineScope

val hours = MutableList(12) { it + 1 }
val minutes = MutableList(60) { it }

@Composable
fun FlatTimePickerItem(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    numbers: List<Int>,
    @StringRes unit: Int? = null,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        VerticalDivider(
            modifier = Modifier.height(MaterialTheme.sizing.large32)
        )
        InfiniteScrollList(
            numbers = numbers,
            listState = listState,
            coroutineScope = coroutineScope,
        )
        VerticalDivider(
            modifier = Modifier.height(MaterialTheme.sizing.large32),
        )
        unit?.let {
            Text(
                text = stringResource(id = unit),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(start = MaterialTheme.spacing.small8),
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun InfiniteLazyColumnPreview() {
    MedicineReminderTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                FlatTimePickerItem(
                    numbers = hours,
                    unit = R.string.hr,
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun InfiniteLazyColumnPreview2() {
    MedicineReminderTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large52)) {
                    FlatTimePickerItem(
                        numbers = hours,
                        unit = R.string.hr,
                    )
                    FlatTimePickerItem(
                        numbers = minutes,
                        unit = R.string.min,
                    )

                }
            }
        }
    }
}

