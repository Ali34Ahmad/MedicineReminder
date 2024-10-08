package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_interaction_note

import androidx.lifecycle.ViewModel
import com.example.medicinereminder.presentation.ui.model.SelectableItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InteractionsAndNoteViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(InteractionsAndNoteUiState())
    val uiState = _uiState.asStateFlow()

    fun onIntent(interactionsAndNoteIntent: InteractionsAndNoteIntent) {
        when (interactionsAndNoteIntent) {
            is InteractionsAndNoteIntent.AddInteraction -> addInteractionEvent()
            is InteractionsAndNoteIntent.UpdateInteractionText -> updateInteractionText(
                interactionsAndNoteIntent.text
            )

            is InteractionsAndNoteIntent.CloseInteractionTextField -> closeInteractionEvent()
            is InteractionsAndNoteIntent.SaveInteraction -> saveInteractionEvent()
            is InteractionsAndNoteIntent.EditInteractionEvent -> editInteractionEvent()
            is InteractionsAndNoteIntent.UpdateEditInteractionDialogSelectedItem -> updatedEditInteractionDialogSelectedItemEvent(
                interactionsAndNoteIntent.index,
                interactionsAndNoteIntent.checked
            )

            is InteractionsAndNoteIntent.EditInteractionDialogConfirmButtonClick -> editInteractionDialogConfirmEvent()
            is InteractionsAndNoteIntent.DismissEditInteractionDialog -> editInteractionDialogDismissEvent()

            is InteractionsAndNoteIntent.DeleteInteraction -> removeInteractionEvent()
            is InteractionsAndNoteIntent.UpdateRemoveInteractionDialogSelectedItem -> updatedRemoveInteractionDialogSelectedItemEvent(
                interactionsAndNoteIntent.index,
                interactionsAndNoteIntent.checked
            )

            is InteractionsAndNoteIntent.RemoveInteractionDialogConfirmButtonClick -> removeInteractionDialogConfirmEvent()
            is InteractionsAndNoteIntent.DismissRemoveInteractionsDialog -> removeInteractionsDialogDismissEvent()

            is InteractionsAndNoteIntent.AddNote -> addNoteEvent()
            is InteractionsAndNoteIntent.CloseNoteTextField -> closeNoteEvent()
            is InteractionsAndNoteIntent.UpdateNoteText -> updateNoteText(interactionsAndNoteIntent.text)
            is InteractionsAndNoteIntent.SaveNote -> saveNoteEvent()
            is InteractionsAndNoteIntent.EditNote -> editNoteEvent()
            is InteractionsAndNoteIntent.DeleteNote -> removeNoteEvent()
            is InteractionsAndNoteIntent.DismissDeleteNoteConfirmationDialog -> deleteNoteConfirmationDialogDismissEvent()
            is InteractionsAndNoteIntent.DeleteNoteConfirmationDialogConfirmButtonClick -> deleteNoteConfirmationDialogConfirmEvent()

        }
    }


    private fun addInteractionEvent() {
        _uiState.update {
            val hasSelectedInteraction =
                it.interactions.firstOrNull { item -> item.selected } != null
            if (!hasSelectedInteraction) {
                it.copy(
                    showInteractionTextField = true,
                    showNoteTextField = false,
                    note = it.noteText,
                )
            } else {
                val index = it.interactions.indexOfFirst { it.selected }
                it.copy(
                    showInteractionTextField = true,
                    showNoteTextField = false,
                    note = it.noteText,
                    interactions = it.interactions.apply {
                        set(
                            index,
                            SelectableItem(
                                it.interactionText,
                                false
                            )
                        )
                    },
                    interactionText = "",
                )

            }
        }
    }

    private fun updateInteractionText(value: String) {
        _uiState.update {
            it.copy(interactionText = value)
        }
    }

    private fun closeInteractionEvent() {
        clearAllSelectedItems()
        _uiState.update {
            it.copy(
                showInteractionTextField = false,
                interactionText = ""
            )
        }
    }

    private fun saveInteractionEvent() {
        _uiState.update {
            it.copy(
                interactions = it.interactions.apply {
                    if (it.interactionText.isNotBlank())
                        if (it.interactions.firstOrNull { item -> item.selected } == null) {
                            add(
                                SelectableItem(
                                    it.interactionText,
                                    false
                                )
                            )
                        } else {
                            val index = it.interactions.indexOfFirst { item -> item.selected }
                            set(
                                index,
                                SelectableItem(
                                    it.interactionText,
                                    false
                                )
                            )
                        }
                },
                showInteractionTextField = false,
                interactionText = "",
            )
        }
    }

    private fun editInteractionEvent() {
        saveInteractionEvent()
        saveNoteEvent()
        _uiState.update {
            it.copy(
                showEditInteractionDialog = true,
            )
        }
    }

    private fun updatedEditInteractionDialogSelectedItemEvent(index: Int, checked: Boolean) {
        _uiState.update {
            it.copy(
                interactions = it.interactions.mapIndexed { i, item ->
                    if (i == index) {
                        item.copy(selected = checked)
                    } else {
                        item.copy(selected = false)
                    }
                }.toMutableList()
            )
        }
    }

    private fun editInteractionDialogConfirmEvent() {
        _uiState.update {
            if (it.interactions.firstOrNull { item -> item.selected } == null) {
                it.copy(
                    showEditInteractionDialog = false,
                )
            } else {
                it.copy(
                    showEditInteractionDialog = false,
                    showInteractionTextField = true,
                    interactionText = it.interactions.first { item -> item.selected }.value,
                    note = it.noteText,
                    noteText = "",
                )
            }
        }
    }

    private fun editInteractionDialogDismissEvent() {
        clearAllSelectedItems()
        _uiState.update {
            it.copy(
                showEditInteractionDialog = false,
            )
        }
    }

    private fun removeInteractionEvent() {
        clearAllSelectedItems()
        _uiState.update {
            it.copy(
                showRemoveInteractionDialog = true,
            )
        }
    }

    private fun updatedRemoveInteractionDialogSelectedItemEvent(index: Int, checked: Boolean) {
        _uiState.update {
            it.copy(
                interactions = it.interactions.mapIndexed { i, item ->
                    if (i == index) {
                        item.copy(selected = checked)
                    } else {
                        item
                    }
                }.toMutableList()
            )
        }
    }

    private fun removeInteractionDialogConfirmEvent() {
        _uiState.update {
            it.copy(
                interactions = it.interactions.filterNot { item ->
                    item.selected
                }.toMutableList(),
                showRemoveInteractionDialog = false,
            )
        }
    }

    private fun removeInteractionsDialogDismissEvent() {
        clearAllSelectedItems()
        _uiState.update {
            it.copy(
                showRemoveInteractionDialog = false,
            )
        }
    }

    private fun clearAllSelectedItems() {
        _uiState.update {
            it.copy(
                interactions = it.interactions.map { item -> item.copy(selected = false) }
                    .toMutableList()
            )
        }
    }

    private fun addNoteEvent() {
        saveInteractionEvent()
        _uiState.update {
            it.copy(
                showNoteTextField = true,
                showInteractionTextField = false,
                interactionText = "",
                noteText = ""
            )
        }
    }

    private fun closeNoteEvent() {
        _uiState.update {
            it.copy(
                showNoteTextField = false,
                noteText = "",
            )
        }
    }

    private fun updateNoteText(value: String) {
        _uiState.update {
            it.copy(noteText = value)
        }
    }

    private fun saveNoteEvent() {
        _uiState.update {
            it.copy(
                showNoteTextField = false,
                note = it.noteText,
            )
        }
    }

    private fun editNoteEvent() {
        saveInteractionEvent()
        _uiState.update {
            it.copy(
                showNoteTextField = true,
                noteText = it.note,
                showInteractionTextField = false,
                interactionText = "",
            )
        }
    }

    private fun removeNoteEvent() {
        _uiState.update {
            it.copy(
                showNoteTextField = false,
                noteText = "",
                showDeleteNoteConfirmationDialog = true,
            )
        }
    }

    private fun deleteNoteConfirmationDialogDismissEvent() {
        _uiState.update {
            it.copy(
                showDeleteNoteConfirmationDialog = false,
            )
        }
    }

    private fun deleteNoteConfirmationDialogConfirmEvent() {
        _uiState.update {
            it.copy(
                showNoteTextField = false,
                note = "",
                noteText = "",
                showDeleteNoteConfirmationDialog = false,
            )
        }
    }
}