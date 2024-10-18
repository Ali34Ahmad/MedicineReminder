package com.example.medicinereminder.feature.home_screen.presentation.appointment_bottom_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.domain.usecase.appointment_use_cases.DeleteAppointmentUseCase
import com.example.medicinereminder.domain.usecase.appointment_use_cases.UpdateAppointmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentBottomSheetViewModel @Inject constructor(
    private val updateAppointmentUseCase: UpdateAppointmentUseCase,
    private val deleteAppointmentUseCase: DeleteAppointmentUseCase
): ViewModel(){

    fun onAction(action: AppointmentBottomSheetAction){
        when(action){
            is AppointmentBottomSheetAction.ChangeAppointmentState -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedAppointment = action.appointment.copy(reminderState = action.newState)
                    updateAppointmentUseCase(updatedAppointment)
                }
            }
            is AppointmentBottomSheetAction.DeleteAppointment -> {
                viewModelScope.launch(Dispatchers.IO) {
                    deleteAppointmentUseCase(action.appointment)
                }
            }
            is AppointmentBottomSheetAction.EditAppointment -> TODO("need navigation")
            is AppointmentBottomSheetAction.ViewAppointmentDetails -> TODO("need navigation to details screen")
        }
    }
}