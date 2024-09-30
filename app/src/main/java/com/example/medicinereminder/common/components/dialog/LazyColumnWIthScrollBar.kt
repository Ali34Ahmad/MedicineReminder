package com.example.medicinereminder.common.components.dialog

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.presentation.ui.theme.spacing


@Composable
fun LazyColumnWithScrollBar(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    item: LazyListScope.() -> Unit = {},
) {
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .heightIn(max = 120.dp)
                .fillMaxWidth()
                .simpleVerticalScrollbar(
                    state = lazyListState,
                    width = MaterialTheme.spacing.small8,
                ),
            state = lazyListState,
        ) {
            item()
        }
    }
}

@Composable
private fun Modifier.simpleVerticalScrollbar(
    state: LazyListState, width: Dp = 8.dp
): Modifier {

    var composableSize by remember { mutableStateOf(IntSize.Zero) }

    val scrollbarOffsetY by remember {
        derivedStateOf {
            val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
            if (firstVisibleElementIndex != null) {
                val elementHeight = composableSize.height / state.layoutInfo.totalItemsCount
                firstVisibleElementIndex.toFloat()* elementHeight
            } else {
                0f
            }
        }
    }

    val animatedScrollbarOffsetY by animateFloatAsState(
        targetValue = scrollbarOffsetY,
        animationSpec = tween(durationMillis = 150),
        label = ""
    )
    val color=MaterialTheme.colorScheme.outlineVariant
    return this.onGloballyPositioned { coordinates ->
        composableSize = coordinates.size
    }.drawWithContent {
        drawContent()

        val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index

        if (firstVisibleElementIndex != null) {
            val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
            val scrollbarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight

            drawRoundRect(
                color = color,
                topLeft = Offset(this.size.width - width.toPx(), animatedScrollbarOffsetY),
                size = Size(width.toPx(), scrollbarHeight),
                cornerRadius = CornerRadius(100f,100f)
            )
        }
    }
}