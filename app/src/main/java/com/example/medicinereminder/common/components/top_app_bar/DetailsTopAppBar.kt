@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.medicinereminder.common.components.top_app_bar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import com.example.medicinereminder.R
import com.example.medicinereminder.common.utility.extension.fade
import com.example.medicinereminder.feature.doctor_details.component.menu.DoctorOptionMenu
import com.example.medicinereminder.feature.doctor_details.component.menu.ExtraMenu
import com.example.medicinereminder.presentation.ui.theme.sizing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopAppBar(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    @DrawableRes navigationIcon: Int = R.drawable.ic_arrow_back,
    onNavigation: () -> Unit,
    @DrawableRes menuIcon: Int = R.drawable.ic_more,
    onMenuIconClick: () -> Unit,
    isMenuIconShown: Boolean = true,
    actions : @Composable RowScope.() -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    expandedHeight:Dp = MaterialTheme.sizing.extraLarge,
    collapsedHeight:Dp = MaterialTheme.sizing.large62,
    content: @Composable RowScope.() -> Unit,
    isOptionMenuExpanded: Boolean ,
    onOptionMenuDismissRequest: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    isExtraMenuExpanded: Boolean,
    onAllButtonClick: () -> Unit,
    onDetailsButtonClick: () -> Unit,
    onPhotoButtonClick: () -> Unit,
    onExtraMenuDismissRequest: () -> Unit
) {
    val fraction = scrollBehavior.state.collapsedFraction
    val isCollapsed by remember {
        derivedStateOf {
            scrollBehavior.state.collapsedFraction == 1f
        }
    }
    Box(
        modifier = modifier
    ){
        Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        collapsedHeight.plus((expandedHeight.minus(collapsedHeight)).times(1 - fraction))
                    ),
                contentScale = ContentScale.Crop
            )
        }

        LargeTopAppBar(
            navigationIcon = {
               IconButton(
                    onClick = onNavigation,
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = navigationIcon)
                        , contentDescription = null
                    )
                }

            },
            collapsedHeight = collapsedHeight,
            expandedHeight = expandedHeight,
            title ={ if(fraction>0.9f){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fade(isCollapsed)
                        ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        content()
                    }
            }},
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = Color.Transparent,
            ),
            actions = {
                if (isMenuIconShown) {
                        FilledIconButton(
                            onClick = onMenuIconClick,
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = menuIcon),
                                contentDescription = null
                            )
                        }
                    //option menu
                    DoctorOptionMenu(
                        expanded = isOptionMenuExpanded,
                        onDismissRequest = onOptionMenuDismissRequest,
                        onEditClick = onEdit,
                        onDeleteClick = onDelete
                    )
                    ExtraMenu(
                        expanded = isExtraMenuExpanded,
                        onAllButtonClick = onAllButtonClick,
                        onDetailsButtonClick = onDetailsButtonClick,
                        onPhotoButtonClick = onPhotoButtonClick,
                        onDismissRequest = onExtraMenuDismissRequest
                    )
                }
                else
                    actions()
            },
            scrollBehavior = scrollBehavior,
        )

    }