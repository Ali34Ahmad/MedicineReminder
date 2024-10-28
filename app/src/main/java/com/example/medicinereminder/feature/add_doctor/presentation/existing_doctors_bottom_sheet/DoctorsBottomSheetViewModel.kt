package com.example.medicinereminder.feature.add_doctor.presentation.existing_doctors_bottom_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminder.common.utility.extension.doesMatchSearchQuery
import com.example.medicinereminder.data.local.doctor1
import com.example.medicinereminder.data.local.doctor2
import com.example.medicinereminder.domain.usecase.doctor_use_cases.GetAllDoctorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DoctorsBottomSheetViewModel @Inject constructor(
    private val getAllDoctorsUseCase: GetAllDoctorsUseCase
): ViewModel() {

    private var _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(FlowPreview::class)
    val doctors = searchQuery.debounce(500).combine(
        //getAllDoctorsUseCase()
        flow = flowOf(listOf(doctor1, doctor2))
    ){query,doctors->
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

    fun onAction(action: DoctorsBottomSheetAction){
        when(action){
            is DoctorsBottomSheetAction.ClickDetailsButton -> TODO("need navigation")
            is DoctorsBottomSheetAction.SelectDoctor -> TODO("need navigation")
            is DoctorsBottomSheetAction.UpdateQuery -> {
                _searchQuery.value = action.query
            }
        }
    }
}