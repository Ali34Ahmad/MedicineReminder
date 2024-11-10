package com.example.medicinereminder.feature.add_appointment.component.time_picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing
import kotlin.math.absoluteValue

@Composable
fun PeriodPicker(
    modifier: Modifier = Modifier,
    onPeriodSelected: (String) -> Unit
    ) {
    val periods = listOf("",stringResource(R.string.am),stringResource(R.string.pm),"")
    val state = rememberPagerState(initialPage = 0) { periods.size }
    Box(
        contentAlignment = Alignment.Center
    ) {
        VerticalPager(
            modifier = modifier
                .width(MaterialTheme.spacing.large52)
                .height(MaterialTheme.spacing.extraLarge156),
            state = state,
            pageSize = PageSize.Fixed(52.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) { page ->

            Text(
                modifier = Modifier
                    .size(MaterialTheme.spacing.large52)
                    .wrapContentSize()
                    .graphicsLayer {
                        val pageOffset = (
                                (state.currentPage + 1 - page) + state
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleX = lerp(
                            start = 0.6f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleY = lerp(
                            start = 0.6f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    ,
                text = periods[page].toString(),
                style =  MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
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
        LaunchedEffect(state) {
            snapshotFlow { state.currentPage }.collect {
                onPeriodSelected(periods[(it+1)])
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PeriodPickerPreview() {
    MedicineReminderTheme {
        PeriodPicker {  }
    }
}