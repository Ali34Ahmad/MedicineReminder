package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.components

import androidx.annotation.StringRes
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.buttons.OutlinedIconButtonComponent
import com.example.medicinereminder.common.components.text_field.OutlinedTextFieldComponent
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing


@Composable
fun InteractionAndNoteTextField(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseTextFieldClick: () -> Unit,
    onSaveButtonClick: () -> Unit,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    val focusRequester= remember{ FocusRequester() }
    LaunchedEffect(Unit){
        focusRequester.requestFocus()
    }
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.medium16,
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16)
        ) {
            OutlinedTextFieldComponent(
                value = text,
                onValueChange = onTextChange,
                label = label,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        top = MaterialTheme.spacing.medium16,
                        bottom = MaterialTheme.spacing.medium16,
                    )
                    .focusRequester(focusRequester),
                buttonText = R.string.save,
                onTextButtonClick = onSaveButtonClick,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions
            )
            OutlinedIconButtonComponent(
                icon = Icons.Outlined.Close,
                onClick = onCloseTextFieldClick,
                modifier = Modifier
                    .size(
                        MaterialTheme.sizing.large56
                    ),
            )
        }
        HorizontalDivider(
            thickness = MaterialTheme.spacing.small1,
            color = MaterialTheme.colorScheme.surfaceContainerLow,
        )
    }
}

@DarkAndLightModePreview
@Composable
fun InteractionAndNoteTextFieldPreview() {
    MedicineReminderTheme {
        Surface {
            InteractionAndNoteTextField(
                text = "",
                onTextChange = {},
                onCloseTextFieldClick = { /*TODO*/ },
                onSaveButtonClick = { /*TODO*/ },
                label = R.string.interaction
            )
        }
    }
}