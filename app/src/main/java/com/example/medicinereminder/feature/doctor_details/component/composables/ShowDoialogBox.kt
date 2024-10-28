package com.example.medicinereminder.feature.doctor_details.component.composables

import androidx.compose.runtime.Composable
import com.example.medicinereminder.feature.doctor_details.component.dialog_box.DeleteAppointmentsDialogBox
import com.example.medicinereminder.feature.doctor_details.component.dialog_box.DeleteDoctorDialogBox
import com.example.medicinereminder.feature.doctor_details.component.dialog_box.StopAppointmentsDialogBox
import com.example.medicinereminder.feature.doctor_details.presentation.DoctorDetailsAction
import com.example.medicinereminder.feature.doctor_details.presentation.DoctorDetailsDialogBox

@Composable
fun ShowDialogBox(
    state: DoctorDetailsDialogBox,
    doctorName: String,
    onAction: (DoctorDetailsAction) -> Unit,
    appointmentNumber: Int
    ) {
    when(state){
        DoctorDetailsDialogBox.NONE ->{}
        DoctorDetailsDialogBox.DELETE_DOCTOR -> {
            DeleteDoctorDialogBox(
                doctorName = doctorName,
                onDismissRequest = {
                    onAction(
                        DoctorDetailsAction.HideDialogBox
                    )
                },
                onCancel = {
                    onAction(
                        DoctorDetailsAction.HideDialogBox
                    )
                },
                onConfirm = {
                    onAction(
                        DoctorDetailsAction.DeleteDoctor
                    )
                }
            )
        }
        DoctorDetailsDialogBox.DELETE_REMINDERS -> {
            DeleteAppointmentsDialogBox(
                appointmentsNumber = appointmentNumber,
                onDismissRequest =  {
                    onAction(
                        DoctorDetailsAction.HideDialogBox
                    )
                },
                onCancel = {
                    onAction(
                        DoctorDetailsAction.HideDialogBox
                    )
                },
                onConfirm = {
                    onAction(
                        DoctorDetailsAction.DeleteAppointments
                    )
                }
            )
        }
        DoctorDetailsDialogBox.STOP_REMINDERS -> {
            StopAppointmentsDialogBox(
                doctorName = doctorName,
                onDismissRequest = {
                    onAction(
                        DoctorDetailsAction.HideDialogBox
                    )
                },
                onConfirm = {
                    onAction(
                        DoctorDetailsAction.StopReminders
                    )
                },
                onCancel = {
                    onAction(
                        DoctorDetailsAction.HideDialogBox
                    )
                },
            )
        }
    }
}