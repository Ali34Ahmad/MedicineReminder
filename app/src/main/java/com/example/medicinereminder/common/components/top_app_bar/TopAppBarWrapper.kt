package com.example.medicinereminder.common.components.top_app_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun TopAppBarWrapper(
    modifier: Modifier = Modifier,
    hasLeadingIcon: Boolean,
    containsTextField:Boolean=false,
    topAppBarContent: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .height(52.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = if (!containsTextField)MaterialTheme.spacing.small4 else MaterialTheme.spacing.default,
                    bottom = if (!containsTextField)MaterialTheme.spacing.small4 else MaterialTheme.spacing.default,
                    end = MaterialTheme.spacing.small4,
                    start = if (hasLeadingIcon)
                        MaterialTheme.spacing.small4
                    else
                        MaterialTheme.spacing.medium16,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            topAppBarContent()
        }
    }
}