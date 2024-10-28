package com.example.medicinereminder.feature.doctor_details.component.menu

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.constants.Icons

@Composable
fun ExtraMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onAllButtonClick: () -> Unit,
    onDetailsButtonClick: () -> Unit,
    onPhotoButtonClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        modifier = modifier,
        expanded =expanded ,
        onDismissRequest = onDismissRequest,
    ){
        DropdownMenuItem(
            text = { 
                Text(text = stringResource(id = R.string.all))
            },
            onClick = onAllButtonClick,
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit_outlined),
                    contentDescription = null
                )
            }
        )
        DropdownMenuItem(
            text = {
                Text(text = stringResource(id = R.string.details))
            },
            onClick = onDetailsButtonClick,
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_details_outlined),
                    contentDescription = null
                )
            }
        )
        DropdownMenuItem(
            text = {
                Text(text = stringResource(id = R.string.photo))
            },
            onClick = onPhotoButtonClick,
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_image_outlined),
                    contentDescription = null
                )
            }
        )
    }
}