package com.example.medicinereminder.common.components.composables

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicinereminder.R
import com.example.medicinereminder.core.components.buttons.TextButtonWithIcon
import com.example.medicinereminder.core.components.list_items.FlowRowItem
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MedicalForm(
    modifier: Modifier = Modifier,
    items: List<String> = emptyList(),
    title: String,
    firstStateText: String,
    secondStateText: String,
    @DrawableRes firstStateIcon: Int,
    @DrawableRes secondStateIcon: Int,
    selectedItemIndex: Int? = null,
    onChooseItem: (Int) ->Unit,
    isExpanded: Boolean = false,
    onExpandClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.Start)
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 1000,
                    )
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            maxLines = if(!isExpanded) 2 else 10
        ){

            items.forEachIndexed{  index, item  ->
                FlowRowItem(
                    label = item,
                    onClick = {onChooseItem(index)},
                    selected = selectedItemIndex == index
                )
            }
        }
        AnimatedContent(
            targetState = isExpanded,
            label = "",
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(500)
                ) togetherWith fadeOut(tween(500))
            }
        ) { targetState ->
           if(!targetState) {
               TextButtonWithIcon(
                   onClick = { onExpandClick()},
                   text = firstStateText,
                   icon = firstStateIcon,
                   modifier = Modifier.padding(16.dp)
               )
           }else{
               TextButtonWithIcon(
                   onClick = { onExpandClick()},
                   text = secondStateText,
                   icon = secondStateIcon,
                   modifier = Modifier.padding(16.dp)
               )
           }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun CollapsedMedicalFormPreview() {
    MedicineReminderTheme {
        MedicalForm(
            title = "Medical Form",
            items = listOf(
                "Capsule", "Tablet", "Syrup","Suspension",
                "Injection","Capsule","Suppository"
            ),
            onChooseItem = {},
            onExpandClick = {},
            firstStateText = "Show all",
            secondStateText ="Show less",
            firstStateIcon = R.drawable.baseline_expand_more_24,
            secondStateIcon = R.drawable.baseline_expand_less_24,
            selectedItemIndex = 3
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ExpandedMedicalFormPreview() {
    MedicineReminderTheme {
        MedicalForm(
            title = "Medical Form",
            items = listOf(
                "Capsule", "Tablet", "Syrup","Suspension",
                "Injection","Capsule","Suppository"
            ),
            onChooseItem = {},
            onExpandClick = {},
            firstStateText = "Show all",
            secondStateText ="Show less",
            firstStateIcon = R.drawable.baseline_expand_more_24,
            secondStateIcon = R.drawable.baseline_expand_less_24,
            selectedItemIndex = 3,
            isExpanded = true
        )
    }
}