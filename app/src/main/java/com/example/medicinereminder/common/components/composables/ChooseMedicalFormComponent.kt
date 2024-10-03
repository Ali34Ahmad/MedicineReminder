package com.example.medicinereminder.common.components.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.buttons.TextButtonWithIcon
import com.example.medicinereminder.common.components.list_item.MedicineFormFlowRowItem
import com.example.medicinereminder.data.local.entity.PharmaceuticalForm
import com.example.medicinereminder.data.local.pharmaceuticalForms
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.constants.Icons
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChooseMedicalFormComponent(
    modifier: Modifier = Modifier,
    items: List<PharmaceuticalForm> = emptyList(),
    @StringRes title: Int,
    @DrawableRes collapsedStateIcon: Int,
    @DrawableRes expandedStateIcon: Int,
    selectedItemIndex: Int? = null,
    @StringRes buttonTextExpandedState: Int,
    @StringRes buttonTextCollapsedState: Int,
    onChooseItem: (index: Int) -> Unit,
    onDeleteItem: (index: Int) -> Unit,
    onAddFormClick: () -> Unit,
    isExpanded: Boolean,
    onButtonClick: () -> Unit
) {
    AnimatedContent(
        targetState = isExpanded,
        label = "",
        transitionSpec = {
            fadeIn(
                animationSpec = tween(500)
            ) togetherWith fadeOut(tween(500))
        }
    ) { targetState ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium16),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.small8)
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 1000,
                        )
                    ),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                maxLines = if (!isExpanded) 2 else Int.MAX_VALUE,
            ) {

                items.forEachIndexed { index, item ->
                    val trailingIcon = if (item.isAddedByUser) {
                        Icons.Outlined.Close
                    } else null
                    MedicineFormFlowRowItem(
                        text = item.name,
                        onClick = { onChooseItem(index) },
                        selected = selectedItemIndex == index,
                        trailingIcon = trailingIcon,
                        onTrailingIconClick = { onDeleteItem(index) }
                    )
                }
                MedicineFormFlowRowItem(
                    text = stringResource(id = R.string.add_form),
                    onClick = { onAddFormClick() },
                    selected = false,
                    trailingIcon = Icons.Outlined.Add,
                    onTrailingIconClick = { onAddFormClick() }
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))

            if (!targetState) {
                TextButtonWithIcon(
                    onClick = { onButtonClick() },
                    text = buttonTextExpandedState,
                    icon = collapsedStateIcon,
                )
            } else {
                TextButtonWithIcon(
                    onClick = { onButtonClick() },
                    text = buttonTextCollapsedState,
                    icon = expandedStateIcon,
                )
            }
        }

    }

}

@DarkAndLightModePreview
@Composable
fun CollapsedMedicalFormPreview() {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedItemIndex by remember {
        mutableStateOf<Int?>(null)
    }

    MedicineReminderTheme {
        Surface {
            Column(
                modifier = Modifier.verticalScroll(state = rememberScrollState())
            ) {
                ChooseMedicalFormComponent(
                    title = R.string.medicine_form,
                    items = pharmaceuticalForms,
                    onChooseItem = { index ->
                        selectedItemIndex = index
                    },
                    onButtonClick = {
                        isExpanded = !isExpanded
                    },
                    collapsedStateIcon = Icons.Outlined.Expand,
                    expandedStateIcon = Icons.Outlined.Collapse,
                    selectedItemIndex = selectedItemIndex,
                    isExpanded = isExpanded,
                    buttonTextCollapsedState = R.string.show_less,
                    buttonTextExpandedState = R.string.show_more,
                    onAddFormClick = {},
                    onDeleteItem = {},
                )
            }

        }
    }
}

@DarkAndLightModePreview
@Composable
fun ExpandedMedicalFormPreview() {
    var isExpanded by remember {
        mutableStateOf(true)
    }
    var selectedItemIndex by remember {
        mutableStateOf<Int?>(null)
    }
    MedicineReminderTheme {
        Surface {
            Column(
                modifier = Modifier.verticalScroll(state = rememberScrollState())
            ) {
                ChooseMedicalFormComponent(
                    title = R.string.medicine_form,
                    items = pharmaceuticalForms,
                    onChooseItem = { index ->
                        selectedItemIndex = index
                    },
                    onButtonClick = {
                        isExpanded = !isExpanded
                    },
                    collapsedStateIcon = Icons.Outlined.Expand,
                    expandedStateIcon = Icons.Outlined.Collapse,
                    selectedItemIndex = selectedItemIndex,
                    isExpanded = isExpanded,
                    buttonTextCollapsedState = R.string.show_less,
                    buttonTextExpandedState = R.string.show_more,
                    onAddFormClick = {},
                    onDeleteItem = {},
                )
            }
        }
    }
}