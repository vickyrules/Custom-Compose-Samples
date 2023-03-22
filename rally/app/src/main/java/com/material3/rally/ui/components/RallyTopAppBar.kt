package com.material3.rally.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.material3.rally.R
import com.material3.rally.data.RallyDestinations
import com.material3.rally.ui.components.common.RallyDivider
import java.util.*

private val TabHeight = 56.dp
private val TabHeightInner = 46.dp
private const val InactiveTabOpacity = 0.60f
private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RallyTopAppBar(
    allScreens: List<RallyDestinations>,
    onTabSelected: (RallyDestinations) -> Unit,
    currentScreen: RallyDestinations
) {

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        TopAppBar(title = {/* */ },
            modifier = Modifier.fillMaxWidth(),
            navigationIcon = {
                Row {
                    allScreens.forEach { screen ->
                        TopAppBarTab(title = screen.route,
                            icon = screen.icon,
                            isSelelctedScreen = currentScreen == screen,
                            onTabClicked = { onTabSelected(screen) })
                    }
                }
            })
        RallyDivider(color = Color.LightGray)
    }

}

@Composable
fun TopAppBarTab(
    icon: ImageVector = Icons.Filled.PieChart,
    title: String = stringResource(id = R.string.app_name),
    isSelelctedScreen: Boolean,
    onTabClicked: () -> Unit
) {
    val color = MaterialTheme.colorScheme.onSurface
    val durationMillis =
        if (isSelelctedScreen) TabFadeInAnimationDuration else TabFadeOutAnimationDuration

    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }

    val tabTintColor by animateColorAsState(
        targetValue = if (isSelelctedScreen) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )
    Row {
        Row(
            modifier = Modifier
                .animateContentSize()
                .wrapContentSize()
                .clip(MaterialTheme.shapes.extraLarge).selectable(
                    role = Role.Tab,
                    onClick = onTabClicked,
                    selected = isSelelctedScreen,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        bounded = false,
                        radius = Dp.Unspecified,
                        color = Color.Unspecified
                    )
                ).padding(all = 5.dp).clearAndSetSemantics { contentDescription = title },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = tabTintColor
            )

            if (isSelelctedScreen) {
                Spacer(Modifier.width(12.dp))
                Text(
                    text = title.uppercase(),
                    color = tabTintColor
                )
            }
        }
        Spacer(Modifier.width(12.dp))
    }

}