package com.example.medicinereminder.common.components.tooltip

import androidx.annotation.StringRes
import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolTip(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
) {
    var isVisible by remember {
        mutableStateOf(false)
    }
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Card(
        modifier = modifier
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp() }
            }
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer,
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onLongPress = {
                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                            isVisible = true
                        },
                        onPress = {
                            val press=PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                                interactionSource.emit(PressInteraction.Release(press))
                        }
                    )
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = text),
                modifier = Modifier
                    .padding(16.dp)
            )
        }
        DropdownMenu(
            expanded = isVisible,
            onDismissRequest = { isVisible = false },
            offset = pressOffset.copy(y = pressOffset.y - itemHeight)
        ) {
            DropdownMenuItem(text = { Text(text = "Tooltip description") }, onClick = { /*TODO*/ })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DarkAndLightModePreview
@Composable
fun ToolTipPreview() {
    MedicineReminderTheme {
        Surface {
            LazyColumn() {
                items(5) {
                    ToolTip(
                        text = R.string.delete,
                    )
                }
            }
        }
    }
}

