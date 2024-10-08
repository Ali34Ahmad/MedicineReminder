package com.example.medicinereminder.feature.add_medicine.presentation.add_medicine_info.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.medicinereminder.R
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun InteractionsList(
    interactions:List<kotlin.String>,
    modifier: Modifier = Modifier,
    ) {
    Column(modifier=modifier){
        Text(
            text = stringResource(R.string.interactions),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                start = MaterialTheme.spacing.medium16,
                end = MaterialTheme.spacing.medium16,
                top = MaterialTheme.spacing.small8,
            )
        )
        interactions.forEachIndexed { index,interaction ->
            Text(
                text = "${index+1}. $interaction",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = MaterialTheme.spacing.small8,
                        horizontal = MaterialTheme.spacing.medium16,
                    )
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small8))
        HorizontalDivider(
            thickness = MaterialTheme.spacing.small1,
            color = MaterialTheme.colorScheme.surfaceContainerLow,
        )
    }
}