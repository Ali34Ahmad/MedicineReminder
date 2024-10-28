package com.example.medicinereminder.common.components.time_picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing
import kotlin.Int
import kotlin.collections.listOf
import kotlin.math.absoluteValue

const val MAX_NUMBER_OF_PAGES = 1000
@Composable
fun CustomPicker(
    modifier: Modifier = Modifier,
    items: List<Int> ,
    onItemSelected: (Int) -> Unit = {},
    numberOfPages:Int = MAX_NUMBER_OF_PAGES
) {
    val itemsInPage = items.size
    val numberOfItems = numberOfPages * itemsInPage

    val state = rememberPagerState(
        initialPage = numberOfPages.div(2).times(itemsInPage).minus(1)
    ){
        numberOfItems
    }

        Box(
            contentAlignment = Alignment.Center
        ) {

            VerticalPager(
                modifier = modifier
                    .width(MaterialTheme.spacing.large52)
                    .height(MaterialTheme.spacing.extraLarge156),
                state = state,
                pageSize = PageSize.Fixed(MaterialTheme.spacing.large52),
                horizontalAlignment = Alignment.CenterHorizontally
                ) { index ->
                val item = items[index%itemsInPage]
                val text = if(item in 0..9) "0$item" else item.toString()
                    Text(
                        modifier = Modifier.size(MaterialTheme.spacing.large52).wrapContentSize().graphicsLayer {
                                val pageOffset = (
                                        (state.currentPage+1 - index) + state
                                            .currentPageOffsetFraction
                                        ).absoluteValue

                                alpha = lerp(
                                    start = 0.7f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                                    scaleX = lerp(
                                        start = 0.7f,
                                        stop = 1f,
                                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                    )
                                    scaleY =lerp(
                                        start = 0.7f,
                                        stop = 1f,
                                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                    )
                            }
                            ,
                        text = text,
                        style =  MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary
                    )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(MaterialTheme.spacing.large52)
                    .height(MaterialTheme.spacing.large52)
            ) {
                VerticalDivider(
                    thickness = MaterialTheme.spacing.small1,
                    modifier = Modifier.fillMaxHeight(0.6f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                VerticalDivider(
                    thickness = MaterialTheme.spacing.small1,
                    modifier = Modifier.fillMaxHeight(0.6f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    LaunchedEffect(state) {
        snapshotFlow { state.currentPage }.collect {
            onItemSelected(items[(it+1)%itemsInPage])
        }
    }

    }




@Preview(showBackground = true)
@Composable
fun CustomTimePickerPreview() {
    MedicineReminderTheme {
        val selectedItem = remember {
            mutableIntStateOf(1)
        }
        Row(Modifier.padding(40.dp)) {
            CustomPicker(
                items =listOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12),
                onItemSelected = {
                    selectedItem.intValue = it
                }
            )
            Spacer(Modifier.height(40.dp))
            Text("selected item : "+selectedItem.intValue.toString(), fontSize = 24.sp)
        }
    }
}