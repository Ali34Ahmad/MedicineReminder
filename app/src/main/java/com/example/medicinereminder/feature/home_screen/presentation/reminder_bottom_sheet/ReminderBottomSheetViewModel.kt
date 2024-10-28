package com.example.medicinereminder.feature.home_screen.presentation.reminder_bottom_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.domain.usecase.medicine_reminder_use_cases.DeleteMedicineReminderUseCase
import com.example.medicinereminder.domain.usecase.medicine_reminder_use_cases.UpdateMedicineReminderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderBottomSheetViewModel @Inject constructor(
    private val updateMedicineReminderUseCase: UpdateMedicineReminderUseCase,
    private val deleteMedicineReminderUseCase: DeleteMedicineReminderUseCase
): ViewModel() {

    fun onAction(action: MedicineReminderBottomSheetAction){
        when(action){
            is MedicineReminderBottomSheetAction.ChangeMedicineReminderState ->{
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedMedicineReminder = action.reminder.copy(reminderState = action.newState)
                    updateMedicineReminderUseCase(updatedMedicineReminder)
                }
            }
            is MedicineReminderBottomSheetAction.DeleteMedicineReminder -> {
                deleteMedicineReminderUseCase(action.reminder)
            }
            is MedicineReminderBottomSheetAction.EditMedicineReminder -> TODO("need navigation")
            is MedicineReminderBottomSheetAction.ViewMedicineReminderDetails -> TODO("need navigation to the details screen")
        }
    }
}