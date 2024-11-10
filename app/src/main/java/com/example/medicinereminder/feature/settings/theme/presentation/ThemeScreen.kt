package com.example.medicinereminder.feature.settings.theme.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicinereminder.common.components.top_app_bar.StandardTopAppBarComponent
import com.example.medicinereminder.R
import com.example.medicinereminder.common.components.list_item.ListItemWithDescriptionAndTrailingTextButton
import com.example.medicinereminder.common.components.list_item.ListItemWithSwitch
import com.example.medicinereminder.common.components.slider.SliderComponent
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun ThemeScreen(
    onAction: (ThemeAction) -> Unit,
    uiState: ThemeUIState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            StandardTopAppBarComponent(
                showNavigateUp = true,
                title = R.string.theme,
                showTrailingIcon = false,
                onNavigateUpClick = {
                    onAction(
                        ThemeAction.NavigateUp
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = MaterialTheme.spacing.small8,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.color_theme),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = MaterialTheme.spacing.medium16)
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
                ListItemWithSwitch(
                    modifier = Modifier
                        .fillMaxWidth(),
                    title = R.string.night_mode,
                    checked = uiState.isNightModeActivated,
                    onCheckedChange = {
                        onAction(
                            ThemeAction.ToggleNightMode
                        )
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                ListItemWithSwitch(
                    modifier = Modifier
                        .fillMaxWidth(),
                    title = R.string.auto_night_mode,
                    subTitle = stringResource(R.string.auto_night_mode_sub_title),
                    checked = uiState.isAutoModeActivated,
                    onCheckedChange = {
                        onAction(
                            ThemeAction.ToggleAutoMode
                        )
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                ListItemWithSwitch(
                    modifier = Modifier
                        .fillMaxWidth(),
                    title = R.string.dynamic_theme,
                    subTitle = stringResource(R.string.dynamic_theme_sub_title),
                    checked = uiState.isDynamicThemeActivated,
                    onCheckedChange = {
                        onAction(
                            ThemeAction.ToggleDynamicTheme
                        )
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                    )
                ListItemWithDescriptionAndTrailingTextButton(
                    title = R.string.customize,
                    subTitle = stringResource(R.string.customize_sub_title),
                    buttonText = R.string.pick,
                    onClick = {
                        onAction(
                            ThemeAction.CustomizeTheme
                        )
                    },
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceContainerLow

                )
                OutlinedButton(
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium16) ,
                    onClick = {
                        TODO("Not yet implemented")
                    }
                ) {
                    Text(text = stringResource(R.string.reset_defaul_colors))
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = MaterialTheme.spacing.small8,
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
                Text(
                    text = stringResource(R.string.font_size)+ " = ${uiState.fontSize}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(
                            start = MaterialTheme.spacing.medium16,
                            top = MaterialTheme.spacing.small8
                        )
                )
                val steps = 5
                val stepValue = 100f.div(steps)
                SliderComponent(
                    modifier = Modifier.padding(
                        vertical = MaterialTheme.spacing.small8,
                        horizontal = MaterialTheme.spacing.medium28
                    ),
                    value = uiState.fontSize.toFloat().times(stepValue),
                    steps = steps,
                    onValueChange = {
                        onAction(
                            ThemeAction.ChangeFontSize(it.div(stepValue).toInt())
                        )
                    }
                )
                OutlinedButton(
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium16) ,
                    onClick = {
                        onAction(
                            ThemeAction.ResetFontsSettings
                        )
                    }
                ) {
                    Text(text = stringResource(R.string.reset_defaul_size))
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThemeScreenPreview() {
    MedicineReminderTheme {
        var  uiState = remember{
            mutableStateOf(ThemeUIState())
        }
        ThemeScreen(
            onAction = {
                when(it){
                    is ThemeAction.ChangeFontSize -> {
                        uiState.value = uiState.value.copy(fontSize = it.newSize)
                    }
                    ThemeAction.CustomizeTheme -> TODO()
                    ThemeAction.NavigateUp -> TODO()
                    ThemeAction.ResetDefaultColorsSettings -> TODO()
                    ThemeAction.ResetFontsSettings -> {}
                    ThemeAction.ToggleAutoMode -> TODO()
                    ThemeAction.ToggleDynamicTheme -> TODO()
                    ThemeAction.ToggleNightMode -> TODO()
                }
            },
            uiState = uiState.value
        )
    }
}