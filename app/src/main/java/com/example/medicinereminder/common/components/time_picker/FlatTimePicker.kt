package com.example.medicinereminder.common.components.time_picker

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val list = MutableList(12) { it + 1 }

@Composable
fun FlatTimePicker(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    val items = remember {
        mutableStateListOf(
            list[0],
            list[1],
            list[2],
            list[3],
            list[4],
            list[5],
            list[6],
            list[7],
            list[8],
            list[9],
            list[10],
            list[11],
        )
    }
    val index = remember { derivedStateOf { listState.firstVisibleItemIndex } }
    LazyColumn(
        modifier = Modifier
            .height(60.dp)
            .width(20.dp), state = listState
    ) {
        items(items) {
            Text(
                text = it.toString(),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
        }

        infiniteScroll(
            listState = listState,
            items = items,
            index = index.value,
            loadMoreToEnd = {
                items.addAll(list)
                Log.v("HoursItems", items.toString())
            },
            loadMoreToStart = {
                items.addAll(0, list)
                Log.v("HoursItems", items.toString())
            },
            removeFirst12Items = {
                items.removeRange(0, 12)
            },
            removeLast12Items = {
                items.removeRange(items.lastIndex - 12, items.lastIndex)
            },
            coroutineScope = coroutineScope,
        )
    }
}

private inline fun LazyListScope.infiniteScroll(
    listState: LazyListState,
    items: List<Any>,
    index: Int,
    crossinline loadMoreToEnd: () -> Unit,
    crossinline loadMoreToStart: () -> Unit,
    crossinline removeFirst12Items: () -> Unit,
    crossinline removeLast12Items: () -> Unit,
    coroutineScope: CoroutineScope,
) {
    if (index + 3 == items.lastIndex && !listState.isScrollInProgress) {
        coroutineScope.launch {
            loadMoreToEnd()
        }
    }
    if (listState.firstVisibleItemIndex == 0 && !listState.isScrollInProgress) {
        coroutineScope.launch {
            loadMoreToStart()
            listState.scrollToItem(listState.firstVisibleItemIndex + 12)
        }
    }
    if(items.size==24&&listState.firstVisibleItemIndex>items.lastIndex-(items.size/4)) {
        coroutineScope.launch {
            removeFirst12Items()
        }
    }
    if(items.size==24&&listState.firstVisibleItemIndex<(items.size/4)) {
        coroutineScope.launch {
            removeLast12Items()
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
                FlatTimePicker()
            }
        }
    }
}