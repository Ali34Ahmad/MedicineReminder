package com.example.medicinereminder.feature.add_appointment.component.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.composables.ContactInfoItem
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun ContactInfoSection(
    modifier: Modifier = Modifier,
    doctor: Doctor,
    isExpanded: Boolean
) {
        AnimatedVisibility(
            modifier = modifier,
            visible = isExpanded,
            enter = fadeIn(tween(400)) + slideInHorizontally(
                animationSpec = tween(400),
            ),
            exit = fadeOut(tween(200))
        ) {
            Column {
                HorizontalDivider(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium16)
                )
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.spacing.medium16
                        )
                        .align(Alignment.Start),
                    text = stringResource(id = R.string.contact_info),
                    style = MaterialTheme.typography.titleMedium
                )
                doctor.phoneNumber?.let {
                    ContactInfoItem(
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(
                                horizontal = MaterialTheme.spacing.medium16
                            ),
                        icon = R.drawable.ic_phone_outlined,
                        text = it,
                        onClick = {}
                    )
                }
                doctor.address?.let {
                    ContactInfoItem(
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(
                                horizontal = MaterialTheme.spacing.medium16
                            ),
                        icon = R.drawable.ic_location_outlined,
                        text = it.toString(),
                        onClick = {}
                    )
                }
            }
        }

}