package com.example.medicinereminder.common.components.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderDefaults.Thumb
import androidx.compose.material3.SliderDefaults.TickSize
import androidx.compose.material3.SliderState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderComponent(
    value: Float,
    onValueChange: (value: Float) -> Unit,
    modifier: Modifier = Modifier,
    steps: Int = 2,
    interactionSource:MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val endIndicatorColor = if (steps - 2 == 0)
        MaterialTheme.colorScheme.secondaryContainer
    else
        MaterialTheme.colorScheme.primary

    Slider(
        modifier = modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.large48),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        thumb = {
            SliderThumb(interactionSource=interactionSource)
        },
        valueRange = 0f..100f,
        track = { sliderState ->
            SliderTrack(sliderState = sliderState, endIndicatorColor = endIndicatorColor)
        },
        steps = steps - 2,
    )
}

@Composable
private fun SliderThumb(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Thumb(
            interactionSource = interactionSource,
            thumbSize = DpSize(
                MaterialTheme.spacing.medium24,
                MaterialTheme.spacing.medium24
            ),
            modifier = Modifier
                .clip(shape = CircleShape)
                .shadow(elevation = MaterialTheme.spacing.small4)
        )
        Spacer(
            modifier = Modifier
                .size(MaterialTheme.spacing.small2)
                .background(
                    MaterialTheme.colorScheme.surface,
                    shape = CircleShape
                )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SliderTrack(
    sliderState: SliderState,
    endIndicatorColor: Color,
    modifier: Modifier = Modifier,
) {
    SliderDefaults.Track(
        sliderState = sliderState,
        thumbTrackGapSize = 0.dp,
        modifier = modifier.height(MaterialTheme.spacing.small4),
        drawStopIndicator = { offset ->
            trackEndIndicator(
                endIndicatorColor = endIndicatorColor,
                offset = offset
            )
        }
    )
}

private fun DrawScope.trackEndIndicator(
    endIndicatorColor: Color,
    offset: Offset,
    modifier: Modifier = Modifier,
) {
    drawCircle(
        color = endIndicatorColor,
        radius = TickSize.toPx() / 2,
        center = offset
    )
}

@DarkAndLightModePreview
@Composable
fun SliderPreview() {
    MedicineReminderTheme {
        var value by remember {
            mutableFloatStateOf(40f)
        }
        Surface {
            SliderComponent(value = value, onValueChange = { value = it })
        }
    }
}

@DarkAndLightModePreview
@Composable
fun SliderWithStepsPreview() {
    MedicineReminderTheme {
        var value by remember {
            mutableFloatStateOf(40f)
        }
        Surface {
            SliderComponent(value = value, onValueChange = { value = it }, steps = 5)
        }
    }
}

