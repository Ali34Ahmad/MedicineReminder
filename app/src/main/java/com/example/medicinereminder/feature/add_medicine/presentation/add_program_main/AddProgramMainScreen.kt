package com.example.medicinereminder.feature.add_medicine.presentation.add_program_main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.buttons.ButtonRow
import com.example.medicinereminder.common.components.cards.ToolTipCard
import com.example.medicinereminder.common.components.tab.ScrollableTab
import com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.day_specific_program.DaySpecificProgramScreen
import com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.day_specific_program.DaySpecificProgramViewModel
import com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.intervals_program.IntervalsProgramScreen
import com.example.medicinereminder.feature.add_medicine.presentation.add_program_main.intervals_program.IntervalsProgramViewModel
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.constants.TabItems
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing
import java.time.LocalDate

@Composable
fun AddProgramMainScreen(
    uiState: AddProgramMainState,
    onIntent: (AddProgramMainIntent)->Unit,
    modifier: Modifier = Modifier,
) {
    val daySpecificProgramViewModel:DaySpecificProgramViewModel= hiltViewModel()
    val daySpecificProgramUiState=daySpecificProgramViewModel.uiState.collectAsState()

    val intervalsProgramViewModel:IntervalsProgramViewModel= hiltViewModel()
    val intervalsProgramUiState=intervalsProgramViewModel.uiState.collectAsState()


    Scaffold(
        modifier = modifier,
    ) { contentPadding ->
        Surface(modifier = modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.large32,
                    )
                ) {
                    ToolTipCard(
                        text = stringResource(R.string.almost_finished_msg),
                        icon =Icons.Outlined.Bulb,
                        modifier=Modifier.padding(horizontal = MaterialTheme.spacing.medium16),
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium12))
//                    ScrollableTab(
//                        itemsStringRes = TabItems.programScreen(),
//                        showBadges = false,
//                        selectedItemIndex = uiState.currentSelectedTabIndex,
//                        onTabClick = {
//                            onIntent(AddProgramMainIntent.UpdateSelectedTabIndex(it))
//                        }
//                    )
                    IntervalsProgramScreen(
                        uiState = intervalsProgramUiState.value,
                        onIntent = intervalsProgramViewModel::onIntent,
                    )
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun AddProgramScreenPreview() {
    MedicineReminderTheme {
        Surface {
            AddProgramMainScreen(
                uiState = AddProgramMainState(),
                onIntent = {},
            )
        }
    }
}