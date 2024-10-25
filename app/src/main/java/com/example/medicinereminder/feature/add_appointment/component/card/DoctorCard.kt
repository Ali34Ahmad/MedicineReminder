package com.example.medicinereminder.feature.add_appointment.component.card

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinereminder.data.local.doctor1
import com.example.medicinereminder.data.local.entity.Doctor
import com.example.medicinereminder.feature.add_appointment.component.composable.ContactInfoSection
import com.example.medicinereminder.feature.add_appointment.component.icon.DoctorCardIconButton
import com.example.medicinereminder.feature.add_appointment.component.image.FloatingImage
import com.example.medicinereminder.presentation.ui.theme.MedicineReminderTheme
import com.example.medicinereminder.presentation.ui.theme.sizing
import com.example.medicinereminder.presentation.ui.theme.spacing

@Composable
fun DoctorCard(
    modifier: Modifier = Modifier,
    doctor: Doctor,
    isExpanded: Boolean = false,
    onExpand: () -> Unit
) {

    Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ){

            val cornerRadius by animateDpAsState(
                animationSpec = tween(400),
                targetValue = if (isExpanded)
                    MaterialTheme.spacing.medium28
                else MaterialTheme.spacing.large36,
                label = ""
            )
            Card(
                modifier = Modifier
                    .padding(top = MaterialTheme.sizing.large36)
                    .graphicsLayer {
                        compositingStrategy = CompositingStrategy.Offscreen
                    }
                    .drawWithCache {
                        val gapSize = size.width / 8f
                        onDrawWithContent {
                            drawContent()
                            drawCircle(
                                Color.Transparent,
                                center = Offset(
                                    x = size.width / 2f,
                                    y = 0f
                                ),
                                radius = gapSize,
                                blendMode = BlendMode.Clear
                            )
                        }
                    },
                shape = RoundedCornerShape(cornerRadius),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)),
                border = BorderStroke(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    width = MaterialTheme.sizing.small1
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = MaterialTheme.spacing.large120)
                        .animateContentSize(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessLow,
                                dampingRatio = Spring.DampingRatioLowBouncy
                            )
                        ).clip(RoundedCornerShape(cornerRadius))
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                ) {
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large48))
                    Text(
                        text = doctor.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small6))
                    Text(
                        text = doctor.specialty,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    //contact section
                    if (!(doctor.phoneNumber == null && doctor.address == null)){
                        ContactInfoSection(doctor = doctor, isExpanded = isExpanded )
                    }

                }
            }
            DoctorCardIconButton(
                isToggled = isExpanded,
                onClick = onExpand
            )
            FloatingImage()
        }
}

@Preview(showBackground = true)
@Composable
fun DoctorCardPreview() {
    MedicineReminderTheme {
        var expanded by remember{
            mutableStateOf(false)
        }

        DoctorCard(
            modifier = Modifier.padding(16.dp),
            doctor = doctor1,
            isExpanded = expanded,
            onExpand = {
                expanded = !expanded
            }
        )
    }
}