package com.example.medicinereminder.feature.appointment_screen.presentation.add_appointment_bottom_sheet

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.common.utility.extension.doesMatchSearchQuery
import com.example.medicinereminder.domain.usecase.doctor_use_cases.GetAllDoctorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AddAppointmentBottomSheetViewModel @Inject constructor(
    private val getAllDoctorsUseCase: GetAllDoctorsUseCase
): ViewModel() {


    private var _searchQuery = MutableStateFlow("")
     val searchQuery = _searchQuery.asStateFlow()


    @OptIn(FlowPreview::class)
    val doctors = getAllDoctorsUseCase().debounce(500).combine(
        searchQuery
    ){doctors,query->
        if(query.isBlank()){
            doctors
        }else{
            doctors.filter {
                it.doesMatchSearchQuery(query)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = emptyList(),
        started = SharingStarted.WhileSubscribed(5000)
        )


    fun onAction(action: AddAppointmentBottomSheetAction){
        when(action){
            is AddAppointmentBottomSheetAction.UpdateQuery -> {
                _searchQuery.value = action.query
            }
            is AddAppointmentBottomSheetAction.SelectDoctor -> TODO("need navigation here")
            is AddAppointmentBottomSheetAction.ClickDetailsButton -> TODO("need navigation here")
        }
    }
}