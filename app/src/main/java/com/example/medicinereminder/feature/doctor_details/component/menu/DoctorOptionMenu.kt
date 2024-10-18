package com.example.medicinereminder.feature.doctor_details.component.menu

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R

@Composable
fun DoctorOptionMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismissRequest: () ->Unit,
    onEditClick: ()-> Unit,
    onDeleteClick: () -> Unit,
) {
    DropdownMenu(
        modifier = modifier,
        expanded =expanded ,
        onDismissRequest = onDismissRequest,
    ){
        DropdownMenuItem(
            text = {
                Text(text = stringResource(id = R.string.edit))
            },
            onClick = onEditClick,
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit_outlined),
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null
                )
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(id = R.string.delete),
                    color =  MaterialTheme.colorScheme.error
                    )
            },
            onClick = onDeleteClick,
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete_outlined),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            },
        )
    }
}