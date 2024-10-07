package com.example.medicinereminder.common.components.composables

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.presentation.ui.helper.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    numberOfPages: Int,
    currentPage: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for(pageNumber in 1..numberOfPages){
            
            val indicatorTransition = updateTransition(
                targetState = currentPage,
                label = "indicatorTransition"
            )
            val color by indicatorTransition.animateColor(
                label = "animatedColor",
                transitionSpec = {
                    tween(400)
                }
            ) {target ->
                if(target==pageNumber){
                    MaterialTheme.colorScheme.primary
                }else
                    MaterialTheme.colorScheme.primaryContainer
            }

            val scaleY by indicatorTransition.animateFloat(
                label = "animatedWidth",
                transitionSpec = {
                    tween(400)
                }
            ) { target ->
                if (target == pageNumber)
                    3f
                else 2f
            }

            Box(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small2)
                    .size(scaleY.times(6).dp, MaterialTheme.sizing.small6)

                    .clip(CircleShape)
                    .background(color)
                ,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun PageIndicatorPreview() {
    var currentPage by remember{
        mutableIntStateOf(1)
    }
    MedicineReminderTheme {
        Surface{
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PageIndicator(
                    numberOfPages = 3,
                    currentPage = currentPage,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { currentPage++ }) {
                    Text(text = "Next")
                }
            }
        }
    }
}