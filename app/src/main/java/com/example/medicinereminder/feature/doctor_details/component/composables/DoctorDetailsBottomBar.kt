package com.example.medicinereminder.feature.doctor_details.component.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.buttons.ButtonRow
import com.example.medicinereminder.feature.doctor_details.component.menu.MarkAsMenu
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@Composable
fun DoctorDetailsBottomBar(
    modifier: Modifier = Modifier,
    isStandard: Boolean,
    onStopButtonClick: () -> Unit,
    onBookButtonClick: () -> Unit,
    onMarkAsButtonClick: () -> Unit,
    isMenuExpanded: Boolean,
    onDismissRequest: () -> Unit,
    onCompleteButtonClick: () ->Unit,
    onMissedButtonClick: () -> Unit
) {

    AnimatedContent(
        targetState = isStandard, label = "",
        modifier = modifier,
        transitionSpec = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(durationMillis = 500)
            ) + fadeIn(tween(500)) togetherWith slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(durationMillis = 400)
            ) + fadeOut(tween(400))
        }
    ) { targetState: Boolean ->
        when(targetState) {
            true -> {
                ButtonRow(
                    primaryButtonText = R.string.book,
                    onPrimaryButtonClick = onBookButtonClick,
                    secondaryButtonText = R.string.stop_reminders,
                    onSecondaryButtonClick = onStopButtonClick
                )
            }
            false -> {
                ButtonRow(
                    primaryButtonText = R.string.mark_as,
                    onPrimaryButtonClick = onMarkAsButtonClick,
                    secondaryButtonText = R.string.stop_reminders,
                    onSecondaryButtonClick = onStopButtonClick
                )
                MarkAsMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = onDismissRequest,
                    onCompletedButtonClick = onCompleteButtonClick,
                    onMissedButtonClick = onMissedButtonClick
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DoctorDetailsBottomBarPreview() {
    MedicineReminderTheme {
        var isStandard by remember {
            mutableStateOf(true)
        }
        DoctorDetailsBottomBar(
            isStandard = isStandard,
            onStopButtonClick = { isStandard = !isStandard },
            onBookButtonClick = { /*TODO*/ },
            onMarkAsButtonClick = { /*TODO*/ },
            isMenuExpanded = false,
            onDismissRequest = { /*TODO*/ },
            onCompleteButtonClick = { /*TODO*/ },
            onMissedButtonClick = {}
        )
    }
}