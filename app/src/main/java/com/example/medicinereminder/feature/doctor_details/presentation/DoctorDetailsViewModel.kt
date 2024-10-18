package com.example.medicinereminder.feature.doctor_details.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.common.ext.extension.toDateFormattedString
import com.example.medicinereminder.common.ext.extension.toLocalDateTime
import com.example.medicinereminder.common.ext.extension.toTimeFormattedString
import com.example.medicinereminder.common.model.AppointmentTableItemInfo
import com.example.medicinereminder.data.enums.ReminderState
import com.example.medicinereminder.data.local.entity.Appointment
import com.example.medicinereminder.domain.usecase.appointment_use_cases.DeleteAppointmentUseCase
import com.example.medicinereminder.domain.usecase.appointment_use_cases.GetAppointmentsByDoctorId
import com.example.medicinereminder.domain.usecase.appointment_use_cases.UpdateAppointmentUseCase
import com.example.medicinereminder.domain.usecase.doctor_use_cases.DeleteDoctorUseCase
import com.example.medicinereminder.domain.usecase.doctor_use_cases.GetDoctorByIdUseCase
import com.example.medicinereminder.presentation.ui.helper.appointmentTableItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorDetailsViewModel @Inject constructor(
    private val getDoctorByIdUseCase: GetDoctorByIdUseCase,
    private val getAppointmentsByDoctorId: GetAppointmentsByDoctorId,
    private val deleteDoctorUseCase: DeleteDoctorUseCase,
    private val updateAppointmentUseCase: UpdateAppointmentUseCase,
    private val deleteAppointmentsUseCase: DeleteAppointmentUseCase,
): ViewModel() {
    private val fakeId = 1
    val doctor = getDoctorByIdUseCase(fakeId).stateIn(
        scope = viewModelScope,
        initialValue = null,
        started = SharingStarted.WhileSubscribed(5000)
    )

    private val _uiState = mutableStateOf(DoctorDetailsUIState())
    val uiState: State<DoctorDetailsUIState> = _uiState

    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments.asStateFlow()

    //fake initial value
    private val _tableItems = MutableStateFlow<List<AppointmentTableItemInfo>>(appointmentTableItems)
    val tableItems: StateFlow<List<AppointmentTableItemInfo>> = _tableItems.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAppointmentsByDoctorId(fakeId).collect{
                _appointments.value = it
                it.map { appointment ->
                    AppointmentTableItemInfo(
                        date = appointment.dateTime.toLocalDateTime().toDateFormattedString(),
                        time = appointment.dateTime.toLocalDateTime().toTimeFormattedString(),
                        state = appointment.reminderState,
                        modifiedAt = appointment.lastModifiedDate.toString(),
                        selected = false,
                        appointmentId = appointment.id
                    )
                }
            }
        }
    }

    fun onAction(action: DoctorDetailsAction){
        when(action){
            is DoctorDetailsAction.ChangeAppointmentsState -> {
                viewModelScope.launch (Dispatchers.IO){
                    val selectedItems = tableItems.value.withIndex().filter {
                        it.value.selected
                    }.map { it.index }
                    selectedItems.forEach { index ->
                        val updatedAppointment = appointments.value[index].copy(reminderState = action.newState)
                        updateAppointmentUseCase(updatedAppointment)
                    }
                }
            }
            DoctorDetailsAction.CloseMarkAsMenu -> {
                _uiState.value = _uiState.value.copy(isMarkAsMenuShown = false)
            }
            DoctorDetailsAction.OpenMarkAsMenu -> {
                _uiState.value = _uiState.value.copy(isMarkAsMenuShown = true)
            }
            DoctorDetailsAction.CloseOptionsMenu -> {
                _uiState.value = _uiState.value.copy(isOptionsMenuShown = false)
            }
            DoctorDetailsAction.OpenOptionsMenu -> {
                _uiState.value = _uiState.value.copy(isOptionsMenuShown = true)
            }
            DoctorDetailsAction.DeleteAppointments -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val selectedItems = tableItems.value.withIndex().filter {
                        it.value.selected
                    }.map { it.index }
                    if(selectedItems.isEmpty()){
                        appointments.value.forEach {
                            deleteAppointmentsUseCase(it)
                        }
                    }
                    selectedItems.forEach {
                        deleteAppointmentsUseCase(appointments.value[it])
                    }
                }
            }
            DoctorDetailsAction.DeleteDoctor -> {
                doctor.value?.let {
                    deleteDoctorUseCase(doctor = it)
                }
            }
            DoctorDetailsAction.HideEditButton -> {
                _uiState.value = _uiState.value.copy(isEditButtonShown = false)
            }
            DoctorDetailsAction.ShowEditButton -> {
                _uiState.value = _uiState.value.copy(isEditButtonShown = true)
            }
            DoctorDetailsAction.HideMarkAsButton -> {
                _uiState.value = _uiState.value.copy(isMarkAsButtonShown = false)
            }
            DoctorDetailsAction.ShowMarkAsButton -> {
                _uiState.value = _uiState.value.copy(isMarkAsButtonShown = true)
            }
            DoctorDetailsAction.StopReminders -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val selectedItems = tableItems.value.withIndex().filter {
                        it.value.selected
                    }.map { it.index }
                    selectedItems.forEach {
                        val updatedAppointment = appointments.value[it].copy(reminderState = ReminderState.STOPPED)
                        updateAppointmentUseCase(updatedAppointment)
                    }
                }
            }
            is DoctorDetailsAction.UpdateTableItemSelection -> {

                val updatedItem = action.tableItem.copy(selected = !action.tableItem.selected)

                val tempList = _tableItems.value.toMutableList()
                tempList[tempList.indexOf(action.tableItem)] = updatedItem

                _tableItems.value = tempList

                val selectedItems = tableItems.value.filter {
                    it.selected
                }
                // change the top bar content if needed
                if(selectedItems.isEmpty() && _uiState.value.topAppBarState == TopAppBarState.ACTIONS)
                    onAction(
                        DoctorDetailsAction.UpdateTopAppBarState(TopAppBarState.INFO)
                    )
                else if(selectedItems.isNotEmpty() && _uiState.value.topAppBarState == TopAppBarState.INFO)
                    onAction(
                        DoctorDetailsAction.UpdateTopAppBarState(TopAppBarState.ACTIONS)
                    )

                //show or hide the edit button if needed
                if(selectedItems.size == 1)
                    onAction(DoctorDetailsAction.ShowEditButton)
                else if(_uiState.value.isEditButtonShown)
                    onAction(DoctorDetailsAction.HideEditButton)
                //show mark as button in the bottom bar
                if(selectedItems.isNotEmpty() && !_uiState.value.isMarkAsButtonShown)
                    onAction(
                        DoctorDetailsAction.ShowMarkAsButton
                    )
                else if(selectedItems.isEmpty())
                    onAction(
                        DoctorDetailsAction.HideMarkAsButton
                    )

            }
            is DoctorDetailsAction.UpdateTap -> {
                _uiState.value = _uiState.value.copy(selectedTab = action.tab)
                _tableItems.value = _tableItems.value.map {  it.copy(selected = false)}.toMutableList()
            }
            is DoctorDetailsAction.UpdateTopAppBarState -> {
                _uiState.value = _uiState.value.copy(topAppBarState = action.newState)
            }
            DoctorDetailsAction.CloseExtraDetailsMenu -> {
                _uiState.value = _uiState.value.copy(isExtraDetailsMenuOpen = false)
            }
            DoctorDetailsAction.OpenExtraDetailsMenu -> {
                _uiState.value = _uiState.value.copy(isExtraDetailsMenuOpen = true)
            }
            DoctorDetailsAction.ShowDeleteDoctorDialogBox -> {
                _uiState.value = _uiState.value.copy(
                    showingDialogBox = DoctorDetailsDialogBox.DELETE_DOCTOR
                )
            }
            DoctorDetailsAction.ShowDeleteRemindersDialogBox -> {
                _uiState.value = _uiState.value.copy(
                    showingDialogBox = DoctorDetailsDialogBox.DELETE_REMINDERS
                )
            }

            DoctorDetailsAction.ShowStopRemindersDialogBox -> {
                _uiState.value = _uiState.value.copy(
                    showingDialogBox = DoctorDetailsDialogBox.STOP_REMINDERS
                )
            }
            DoctorDetailsAction.HideDialogBox -> {
                _uiState.value = _uiState.value.copy(
                    showingDialogBox = DoctorDetailsDialogBox.NONE
                )
            }
            DoctorDetailsAction.NavigateBack -> TODO("need navigation here")
            is DoctorDetailsAction.EditAppointment -> TODO("need navigation to the edit appointment screen")
            is DoctorDetailsAction.EditDoctor -> TODO("need navigation to the edit doctor screen")
            is DoctorDetailsAction.GoToContacts -> TODO("need intent to navigate to the contacts: put it in the domain layer")
            is DoctorDetailsAction.GoToMap -> TODO("need navigation to the map")
            is DoctorDetailsAction.BookAppointment -> TODO("need navigation to add appointment screen")
            is DoctorDetailsAction.EditAll -> TODO("need navigation for editing ")
            is DoctorDetailsAction.EditDetails -> TODO("need navigation for editing ")
            is DoctorDetailsAction.EditImage -> TODO("need navigation for editing ")
        }
    }
}