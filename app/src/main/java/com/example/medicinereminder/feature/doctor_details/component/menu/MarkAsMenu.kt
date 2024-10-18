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

@Composable
 fun MarkAsMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismissRequest: () ->Unit,
    onCompletedButtonClick: ()-> Unit,
    onMissedButtonClick: () -> Unit,
) {
   DropdownMenu(
      modifier = modifier,
      expanded =expanded ,
      onDismissRequest = onDismissRequest,
   ){
      DropdownMenuItem(
         leadingIcon = {
            Icon(
               imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit_outlined) ,
               contentDescription = null
            )
                       },
         text = { Text(text = stringResource(id = R.string.completed)) },
         onClick = onCompletedButtonClick,
      )
      DropdownMenuItem(
         leadingIcon = {
            Icon(
               imageVector = ImageVector.vectorResource(id = R.drawable.ic_notification_missed_outlined) ,
               contentDescription = null
            )
                       },
         text = { Text(text = stringResource(id = R.string.missed)) },
         onClick = onMissedButtonClick,
      )
   }
}