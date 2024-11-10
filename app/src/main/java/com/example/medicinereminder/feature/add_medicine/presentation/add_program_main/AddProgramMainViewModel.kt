package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class AddProgramMainViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(AddProgramMainState())
    val uiState = _uiState.asStateFlow()

    fun onIntent(addProgramMainIntent: AddProgramMainIntent){
        when(addProgramMainIntent){
            is AddProgramMainIntent.UpdateSelectedTabIndex->updateSelectedTabIndex(addProgramMainIntent.index)
        }
    }

    private fun updateSelectedTabIndex(index:Int){
        _uiState.update {
            it.copy(currentSelectedTabIndex=index)
        }
    }
}