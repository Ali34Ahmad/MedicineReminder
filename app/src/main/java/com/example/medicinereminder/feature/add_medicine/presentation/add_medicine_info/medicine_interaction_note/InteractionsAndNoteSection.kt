package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_interaction_note

import RichButtonWithIconAndText
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.dialog.DialogWithCheckBoxesLazyColumn
import com.example.medicinereminder.common.components.dialog.DialogWithRadioButtonLazyColumn
import com.example.medicinereminder.common.components.dialog.DialogWithTitleAndDescription
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.components.InteractionAndNoteTextField
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.components.InteractionsList
import com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.components.NoteText
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.model.SelectableItem
import com.example.medicinereminder.presentation.ui.theme.spacing


@Composable
fun AddInteractionAndNoteSection(
    onIntent: (InteractionsAndNoteIntent)->Unit,
    uiState:InteractionsAndNoteUiState,
    modifier: Modifier = Modifier,
    //keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    val dividerThickness by animateDpAsState(
        targetValue = if (uiState.showInteractionTextField || uiState.interactions.isNotEmpty() || uiState.showNoteTextField || uiState.note.isNotBlank())
            MaterialTheme.spacing.small8
        else MaterialTheme.spacing.small1,
        label = ""
    )
//    var textFieldHeight by remember { mutableStateOf(0.dp) }

    DialogWithRadioButtonLazyColumn(
        showDialog = uiState.showEditInteractionDialog,
        description = R.string.choose_which_one_to_edit,
        title = R.string.edit_interactions,
        selectableItems = uiState.interactions,
        confirmButtonText = R.string.confirm,
        dismissButtonText = R.string.cancel,
        onDismissRequest = { onIntent(InteractionsAndNoteIntent.DismissEditInteractionDialog) },
        onConfirmClick = { onIntent(InteractionsAndNoteIntent.EditInteractionDialogConfirmButtonClick) },
        onCheckedChange = { index, checked ->
            onIntent(
                InteractionsAndNoteIntent.UpdateEditInteractionDialogSelectedItem(
                    index,
                    checked
                )
            )
        },
    )
    DialogWithCheckBoxesLazyColumn(
        showDialog = uiState.showRemoveInteractionDialog,
        description = R.string.choose_which_items_to_remove,
        title = R.string.delete_interactions,
        selectableItems = uiState.interactions,
        confirmButtonText = R.string.delete,
        dismissButtonText = R.string.cancel,
        onDismissRequest = { onIntent(InteractionsAndNoteIntent.DismissRemoveInteractionsDialog) },
        onConfirmClick = { onIntent(InteractionsAndNoteIntent.RemoveInteractionDialogConfirmButtonClick) },
        onCheckedChange = { index, checked ->
            onIntent(
                InteractionsAndNoteIntent.UpdateRemoveInteractionDialogSelectedItem(
                    index,
                    checked
                )
            )
        }
    )
    DialogWithTitleAndDescription(
        showDialog = uiState.showDeleteNoteConfirmationDialog,
        description = R.string.delete_note_confirmation_desc,
        title = R.string.delete_note,
        confirmButtonText = R.string.delete,
        dismissButtonText = R.string.cancel,
        onDismissRequest = { onIntent(InteractionsAndNoteIntent.DismissDeleteNoteConfirmationDialog) },
        onConfirmClick = { onIntent(InteractionsAndNoteIntent.DeleteNoteConfirmationDialogConfirmButtonClick) }
    )
    Column(modifier = modifier) {
        AnimatedVisibility(visible = uiState.interactions.isNotEmpty()) {
            InteractionsList(uiState.interactions.toNonSelectableItems())
        }
        AnimatedVisibility(
            visible = uiState.showInteractionTextField
        ) {
            InteractionAndNoteTextField(
                text = uiState.interactionText,
                onTextChange = { onIntent(InteractionsAndNoteIntent.UpdateInteractionText(it)) },
                onCloseTextFieldClick = { onIntent(InteractionsAndNoteIntent.CloseInteractionTextField) },
                onSaveButtonClick = { onIntent(InteractionsAndNoteIntent.SaveInteraction) },
                label = R.string.interaction,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                ),
//                keyboardActions = keyboardActions,
            )
        }
        RichButtonWithIconAndText(
            icon = Icons.Outlined.Interactions,
            title = R.string.add_interaction,
            desc = R.string.add_interaction_desc,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                MaterialTheme.spacing.medium16
            ),
            onClick = { onIntent(InteractionsAndNoteIntent.AddInteraction) },
            enabled = uiState.interactions.firstOrNull { it.selected } == null,
        )
        AnimatedVisibility(visible = uiState.interactions.isNotEmpty()) {
            Column {
                RichButtonWithIconAndText(
                    icon = Icons.Outlined.Edit,
                    title = R.string.edit_interactions,
                    desc = R.string.edit_interactions,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(
                        MaterialTheme.spacing.medium16
                    ),
                    onClick = { onIntent(InteractionsAndNoteIntent.EditInteractionEvent) },
                )
                HorizontalDivider(
                    thickness = MaterialTheme.spacing.small1,
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                )
                RichButtonWithIconAndText(
                    icon = Icons.Outlined.Delete,
                    title = R.string.delete_interactions,
                    desc = R.string.remove_interactions_desc,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(
                        MaterialTheme.spacing.medium16
                    ),
                    onClick = { onIntent(InteractionsAndNoteIntent.DeleteInteraction) },
                )
            }

        }
        HorizontalDivider(
            thickness = dividerThickness,
            color = MaterialTheme.colorScheme.surfaceContainerLow,
        )
        AnimatedVisibility(
            visible = uiState.note.isNotEmpty()
        ) {
            NoteText(note = uiState.note)
        }
        AnimatedVisibility(
            visible = uiState.showNoteTextField
        ) {
            InteractionAndNoteTextField(
                text = uiState.noteText,
                onTextChange = { onIntent(InteractionsAndNoteIntent.UpdateNoteText(it)) },
                onCloseTextFieldClick = { onIntent(InteractionsAndNoteIntent.CloseNoteTextField) },
                onSaveButtonClick = { onIntent(InteractionsAndNoteIntent.SaveNote) },
                label = R.string.note,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                ),
//                keyboardActions = keyboardActions,
            )
        }
        AnimatedVisibility(
            visible = !uiState.showNoteTextField && uiState.note.isEmpty()
        ) {
            RichButtonWithIconAndText(
                icon = Icons.Outlined.Note,
                title = R.string.add_note,
                desc = R.string.add_note_desc,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    MaterialTheme.spacing.medium16
                ),
                onClick = { onIntent(InteractionsAndNoteIntent.AddNote) },
            )
        }
        AnimatedVisibility(
            visible = uiState.note.isNotEmpty()
        ) {
            Column {
                RichButtonWithIconAndText(
                    icon = Icons.Outlined.Edit,
                    title = R.string.edit_note,
                    desc = R.string.edit_note_desc,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(
                        MaterialTheme.spacing.medium16
                    ),
                    onClick = { onIntent(InteractionsAndNoteIntent.EditNote) },
                )
                RichButtonWithIconAndText(
                    icon = Icons.Outlined.Delete,
                    title = R.string.delete_note,
                    desc = R.string.add_note_desc,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(
                        MaterialTheme.spacing.medium16
                    ),
                    onClick = { onIntent(InteractionsAndNoteIntent.DeleteNote) },
                )
            }

        }
    }
}
private fun List<SelectableItem>.toNonSelectableItems(): List<String> {
    return this.map { selectableItem ->
        selectableItem.value
    }
}