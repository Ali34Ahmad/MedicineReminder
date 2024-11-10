package com.example.medicinereminder.common.components.time_picker

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun InfiniteScrollList(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    numbers: List<Int>,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    val items = remember {
        mutableStateListOf<Int>()
            .also {
                it.addAll(numbers)
            }
    }
    val index = remember { derivedStateOf { listState.firstVisibleItemIndex } }

    LazyColumn(
        modifier = modifier
            .height(MaterialTheme.sizing.large132)
            .width(MaterialTheme.sizing.large52),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium18),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = MaterialTheme.spacing.small6)
    ) {
        items(items) {
            Text(
                text = it.toString(),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
            )
        }
        infiniteScroll(
            listState = listState,
            items = items,
            index = index.value,
            loadMoreToEnd = {
                items.addAll(numbers)
                Log.v("HoursItems", items.toString())
            },
            loadMoreToStart = {
                items.addAll(0, numbers)
                Log.v("HoursItems", items.toString())
            },
            removeFirst12Items = {
                items.removeRange(0, numbers.size)
            },
            removeLast12Items = {
                items.removeRange(items.lastIndex - numbers.size, items.lastIndex)
            },
            coroutineScope = coroutineScope,
            numbersListSize = numbers.size,
        )
    }
}

private inline fun LazyListScope.infiniteScroll(
    listState: LazyListState,
    items: List<Any>,
    numbersListSize: Int,
    index: Int,
    crossinline loadMoreToEnd: () -> Unit,
    crossinline loadMoreToStart: () -> Unit,
    crossinline removeFirst12Items: () -> Unit,
    crossinline removeLast12Items: () -> Unit,
    coroutineScope: CoroutineScope,
) {
    if (index + 4 >= items.lastIndex && !listState.isScrollInProgress) {
        coroutineScope.launch {
            loadMoreToEnd()
        }
    }
    if (listState.firstVisibleItemIndex == 0 && !listState.isScrollInProgress) {
        coroutineScope.launch {
            loadMoreToStart()
            listState.scrollToItem(listState.firstVisibleItemIndex + numbersListSize)
        }
    }
    if (items.size == numbersListSize * 2 && listState.firstVisibleItemIndex > items.lastIndex - (items.size / 4)) {
        coroutineScope.launch {
            removeFirst12Items()
        }
    }
    if (items.size == numbersListSize * 2 && listState.firstVisibleItemIndex < (items.size / 4)) {
        coroutineScope.launch {
            removeLast12Items()
        }
    }
}

@DarkAndLightModePreview
@Composable
fun InfiniteLazyColumnPreview1() {
    MedicineReminderTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                InfiniteScrollList(
                    numbers = hours
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun InfiniteLazyColumnPreview21() {
    MedicineReminderTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large52)) {
                    InfiniteScrollList(
                        numbers = hours
                    )
                    InfiniteScrollList(
                        numbers = minutes
                    )

                }
            }
        }
    }
}