package com.example.medicinereminder.common.components.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.medicinereminder.common.components.list_item.AppointmentTableItem
import com.example.medicinereminder.common.model.AppointmentTableItemInfo
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.appointmentsTableTitles
import com.example.medicinereminder.presentation.ui.helper.appointmentTableItems
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun AppointmentTable(
    modifier: Modifier = Modifier,
    tableItems: List<AppointmentTableItemInfo> = emptyList(),
    onItemLongClick:(item:AppointmentTableItemInfo)->Unit,
) {
    var firstItemIsSelected by rememberSaveable {
        mutableStateOf(false)
    }
    if (tableItems.isNotEmpty()) firstItemIsSelected = tableItems[0].selected

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            TitlesRow(
                titles = appointmentsTableTitles.toIntArray(),
                firstItemIsSelected = firstItemIsSelected,
            )
        }

        items(tableItems, key = {it.appointmentId}) { item ->
            val index = tableItems.indexOf(item)
            var nextItemIsSelected=false
            if (index+1<=tableItems.lastIndex) nextItemIsSelected=tableItems[index+1].selected

            AppointmentTableItem(
                appointmentTableItemInfo = tableItems[index],
                nextItemIsSelected=nextItemIsSelected,
                onLongClick = onItemLongClick,
                currentItem = item,
            )
            if (nextItemIsSelected){
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surface,
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun AppointmentTablePreview() {
    MedicineReminderTheme {
        Surface {
            var list by remember{
                mutableStateOf(appointmentTableItems)
            }
            AppointmentTable(
                tableItems = list,
                onItemLongClick = {
                    val item = it
                    val updatedItem = item.copy(selected = ! item.selected)
                    val tempList = list.toMutableList()
                    tempList[list.indexOf(it)] = updatedItem
                    list = tempList
                },
            )
        }
    }
}