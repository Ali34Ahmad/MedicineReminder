package com.example.medicinereminder.feature.settings.notifications.component.dialog_box

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.dialog.DialogWithSelectableItemsLazyColumn
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun RingtoneList(
    modifier: Modifier = Modifier,
    ringtones: List<String>,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    isDialogShown: Boolean,
    selectedRingtone: String,
    onRingtoneSelected: (String) -> Unit
) {

    DialogWithSelectableItemsLazyColumn(
        items = {
            items(ringtones) { ringtone ->
                val backgroundColor =
                    if(ringtone == selectedRingtone)
                        MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                    else MaterialTheme.colorScheme.surfaceContainer
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.medium16,)
                        .background(backgroundColor)
                        .padding(
                            MaterialTheme.spacing.small8,
                        )
                        .clickable {
                            onRingtoneSelected(ringtone)
                        },
                    text = ringtone.toString(),
                )
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16)
                )
            }
        },
        title = R.string.ringtones,
        description = R.string.choose_ringtone,
        confirmButtonText = R.string.confirm,
        dismissButtonText = R.string.cancel,
        onDismissRequest = onDismissRequest,
        onConfirmClick = onConfirmClick,
        showDialog = isDialogShown,
        modifier = modifier,
        lazyListState = rememberLazyListState(),
    )
}

@Preview(showBackground = true)
@Composable
fun RingtoneListPreview() {
    MedicineReminderTheme {
        RingtoneList(
            ringtones = listOf(
                "Ya Ana Ya La (Amr Diab)",
                "Waka Waka (Shakira)",
                "Targhala (Gorge Wassof)",
                "Helif Al Amar (Gorge Wassof)"
            ),
            isDialogShown = true,
            onConfirmClick = {},
            onDismissRequest = {},
            selectedRingtone = "Ya Ana Ya La (Amr Diab)",
            onRingtoneSelected = {}
        )
    }
}