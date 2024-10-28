package com.example.medicinereminder.common.components.list_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.medicinereminder.common.utility.extension.listFromRange
import com.example.medicinereminder.presentation.ui.theme.spacing
import java.time.LocalDate


@Composable
fun DaySocketHorizontalList(
    startDate: LocalDate,
    endDate: LocalDate,
    modifier: Modifier = Modifier,
    selectedDate: LocalDate? = null,
    onDateSelected: (LocalDate) -> Unit,
    ) {
    val daySockets = listFromRange(startDate,endDate)
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
    ) {
        items(daySockets) { date ->
            DaySocketListItem(
                date = date,
                onDateSelected = onDateSelected,
                isSelected = selectedDate == date,
            )
        }
    }
}