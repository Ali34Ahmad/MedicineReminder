package com.example.medicinereminder.common.components.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun DoctorSearchSuggestionMenu(
    doctors: List<Doctor>,
    onDismissRequest: () -> Unit,
    onItemClick: (doctorId: Int) -> Unit,
    onViewDetailsItemClick: (doctorId: Int) -> Unit,
    expanded: Boolean,
    modifier: Modifier = Modifier,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        DoctorSearchSuggestionMenuHeader(onDismissRequest = onDismissRequest)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.outlineVariant)
        )
        doctors.forEachIndexed {index,doctor->
            DoctorSearchSuggestionMenuItem(
                doctor = doctor,
                onItemClick = onItemClick,
                onViewDetailsItemClick = onViewDetailsItemClick,
                isLastItem = index==doctors.size-1
            )
        }
    }
}

@Composable
fun DoctorSearchSuggestionMenuItem(
    doctor: Doctor,
    onItemClick: (doctorId: Int) -> Unit,
    onViewDetailsItemClick: (doctorId: Int) -> Unit,
    isLastItem:Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(doctor.id)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.medium16,
                    end = MaterialTheme.spacing.small8,
                    top = MaterialTheme.spacing.medium16,
                    bottom = if(!isLastItem)MaterialTheme.spacing.small4
                    else MaterialTheme.spacing.small8,
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.doctor_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(MaterialTheme.spacing.large48)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        horizontal = MaterialTheme.spacing.medium16,
                    )
            ) {
                Text(
                    text = doctor.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = doctor.specialty,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.small4),
                )
            }
            IconButton(
                onClick = { onViewDetailsItemClick(doctor.id) }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(Icons.Outlined.ArrowRightUp),
                    contentDescription = stringResource(id = R.string.view_details),
                    tint = MaterialTheme.colorScheme.outline,
                )
            }
        }
    }
}

@Composable
private fun DoctorSearchSuggestionMenuHeader(
    onDismissRequest: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.spacing.medium16,
                end = MaterialTheme.spacing.medium16,
                bottom = MaterialTheme.spacing.small8,
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.already_exist_doctors),
            modifier = Modifier
                .weight(1f)
                .padding(end = MaterialTheme.spacing.small8),
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1,
        )
        IconButton(
            onClick = onDismissRequest,
            modifier = Modifier
                .height(MaterialTheme.spacing.large32)
                .width(MaterialTheme.spacing.large32),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(Icons.Outlined.Close),
                contentDescription = stringResource(
                    id = R.string.close
                ),
                tint = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun DoctorSearchSuggestionMenuPreview() {
    MedicineReminderTheme {
        Surface {
            DoctorSearchSuggestionMenu(
                doctors = listOf(),
                expanded = true,
                onDismissRequest = {},
                onItemClick = {},
                onViewDetailsItemClick = {},
                //TODO("Solve this problem with modifier padding")
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium16)
            )
        }
    }
}