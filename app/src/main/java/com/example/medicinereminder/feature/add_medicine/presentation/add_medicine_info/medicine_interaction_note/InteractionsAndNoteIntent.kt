package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.medicine_interaction_note

sealed interface InteractionsAndNoteIntent {
    data object AddInteraction : InteractionsAndNoteIntent
    data class UpdateInteractionText(val text: String) : InteractionsAndNoteIntent
    data object DeleteInteraction : InteractionsAndNoteIntent
    data object CloseInteractionTextField : InteractionsAndNoteIntent
    data object SaveInteraction : InteractionsAndNoteIntent
    data object EditInteractionEvent : InteractionsAndNoteIntent
    data object EditInteractionDialogConfirmButtonClick : InteractionsAndNoteIntent
    data class UpdateEditInteractionDialogSelectedItem(val index: Int, val checked: Boolean) :
        InteractionsAndNoteIntent

    data object DismissEditInteractionDialog : InteractionsAndNoteIntent
    data object DismissRemoveInteractionsDialog : InteractionsAndNoteIntent
    data object RemoveInteractionDialogConfirmButtonClick : InteractionsAndNoteIntent
    data class UpdateRemoveInteractionDialogSelectedItem(val index: Int, val checked: Boolean) :
        InteractionsAndNoteIntent

    data object AddNote : InteractionsAndNoteIntent
    data object SaveNote : InteractionsAndNoteIntent
    data class UpdateNoteText(val text: String) : InteractionsAndNoteIntent
    data object CloseNoteTextField : InteractionsAndNoteIntent
    data object EditNote : InteractionsAndNoteIntent
    data object DeleteNote : InteractionsAndNoteIntent
    data object DismissDeleteNoteConfirmationDialog : InteractionsAndNoteIntent
    data object DeleteNoteConfirmationDialogConfirmButtonClick : InteractionsAndNoteIntent

}