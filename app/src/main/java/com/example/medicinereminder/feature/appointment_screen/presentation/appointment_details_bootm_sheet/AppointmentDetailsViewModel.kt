package com.example.medicinereminder.feature.appointment_screen.presentation.appointment_details_bootm_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.domain.usecase.appointment_use_cases.DeleteAppointmentUseCase
import com.example.medicinereminder.domain.usecase.appointment_use_cases.UpdateAppointmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentDetailsViewModel @Inject constructor(
    private val deleteAppointmentUseCase: DeleteAppointmentUseCase,
    private val updateAppointmentUseCase: UpdateAppointmentUseCase
): ViewModel(){

    fun onAction(action: AppointmentDetailsBottomSheetAction){
        when(action){
            is AppointmentDetailsBottomSheetAction.ChangeAppointmentState ->{
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedAppointment = action.appointment.copy(reminderState = action.newState)
                    updateAppointmentUseCase(updatedAppointment)
                }
            }
            is AppointmentDetailsBottomSheetAction.DeleteAppointment ->{
                viewModelScope.launch(Dispatchers.IO) {
                    deleteAppointmentUseCase(action.appointment)
                }
            }
            is AppointmentDetailsBottomSheetAction.EditAppointment -> TODO("need navigation here")
            is AppointmentDetailsBottomSheetAction.ViewDetails -> TODO("need navigation to the details screen")
        }
    }
}