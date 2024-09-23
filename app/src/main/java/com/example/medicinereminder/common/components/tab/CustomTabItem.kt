package com.example.medicinereminder.common.components.tab

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.DarkAndLightModePreview
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun CustomTabItem(
    @StringRes textResId:Int,
    onTabClick:(index:Int)->Unit,
    index:Int,
    selected:Boolean,
    showBadges:Boolean,
    modifier: Modifier = Modifier,
    ) {
    Box(
        modifier = modifier
            .heightIn(max = 52.dp)
            .clickable {
                onTabClick(index)
            }
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.medium24,
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = textResId),
                    style = MaterialTheme.typography.titleSmall,
                    color =
                    if (selected)
                        MaterialTheme.colorScheme.onSurface
                    else
                        MaterialTheme.colorScheme.outline
                )
                if (showBadges) {
                    Box(
                        modifier =
                        Modifier
                            .padding(start = MaterialTheme.spacing.small4)
                            .size(MaterialTheme.spacing.medium16)
                            .background(
                                shape = CircleShape,
                                color =
                                if (selected)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.outline
                            ),
                        contentAlignment = Alignment.Center,

                        ) {
                        Text(
                            text = "1",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Spacer(
                modifier = Modifier
                    .width(50.dp)
                    .height(3.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun UnselectedCustomTabItemWithoutBadgesPreview(){
    MedicineReminderTheme{
        Surface{
            CustomTabItem(
                textResId = R.string.medicines,
                onTabClick = {},
                index = 0,
                selected = false,
                showBadges = false,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun SelectedCustomTabItemWithoutBadgesPreview(){
    MedicineReminderTheme{
        Surface{
            CustomTabItem(
                textResId = R.string.medicines,
                onTabClick = {},
                index = 0,
                selected = true,
                showBadges = false,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun UnselectedCustomTabItemWithBadgesPreview(){
    MedicineReminderTheme{
        Surface{
            CustomTabItem(
                textResId = R.string.medicines,
                onTabClick = {},
                index = 0,
                selected = false,
                showBadges = true,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun SelectedCustomTabItemWithBadgesPreview(){
    MedicineReminderTheme{
        Surface{
            CustomTabItem(
                textResId = R.string.medicines,
                onTabClick = {},
                index = 0,
                selected = true,
                showBadges = true,
            )
        }
    }
}



