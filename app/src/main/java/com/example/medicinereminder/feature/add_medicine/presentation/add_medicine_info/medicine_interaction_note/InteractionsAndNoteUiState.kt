package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_interaction_note

import com.example.medicinereminder.presentation.ui.model.SelectableItem

data class InteractionsAndNoteUiState(
    val interactionText: String = "",
    val showInteractionTextField: Boolean = false,
    val interactions: MutableList<SelectableItem> = mutableListOf(),
    val addInteractionRichButtonIsEnabled: Boolean = true,
    val showEditInteractionDialog: Boolean = false,
    val showRemoveInteractionDialog: Boolean = false,

    val noteText: String = "",
    val note: String = "",
    val showNoteTextField: Boolean = false,
    val showDeleteNoteConfirmationDialog: Boolean = false,
)
