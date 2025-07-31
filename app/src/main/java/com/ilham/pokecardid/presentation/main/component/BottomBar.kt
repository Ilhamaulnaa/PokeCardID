package com.ilham.pokecardid.presentation.main.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ilham.pokecardid.presentation.main.navigation.NavigationItem
import com.ilham.pokecardid.presentation.main.navigation.Screen
import com.ilham.pokecardid.R
import com.ilham.pokecardid.ui.theme.SecondaryDark

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var xIndicatorOffset by remember {
        mutableStateOf(Float.NaN)
    }
    val xOffsetAnimated by animateFloatAsState(targetValue = xIndicatorOffset, label = "")

    val home = NavigationItem(
        title = "Home",
        screen = Screen.Home,
        icon = R.drawable.ic_home,
        iconActive = R.drawable.ic_home_active
    )
    val profile = NavigationItem(
        title = "Profile",
        screen = Screen.Profile,
        icon = R.drawable.ic_profile,
        iconActive = R.drawable.ic_profile_active
    )

    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 48.dp)
            .height(65.dp)
            .fillMaxWidth()
            .shadow(0.dp,shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){

        IconButton(
            onClick = {
                navController.navigate(home.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            val selected = currentDestination?.hierarchy?.any { it.route == home.screen.route } == true
            val icon = if (selected) {
                home.iconActive
            } else {
                home.icon
            }
            Image(
                painter = painterResource(id = icon),
                contentDescription = home.title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(26.dp)
            )
        }
        IconButton(
            onClick = {
                navController.navigate(profile.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            val selected = currentDestination?.hierarchy?.any { it.route == profile.screen.route } == true
            val icon = if (selected) {
                profile.iconActive
            } else {
                profile.icon
            }
            Image(
                painter = painterResource(id = icon),
                contentDescription = profile.title
            )
        }

    }

}

@Composable
fun Modifier.drawCircleIndicator(
    xOffset: Float,
    indicatorSize: Dp = 52.dp,
    indicatorColor: Color = Color.White
): Modifier = composed {
    drawBehind {
        drawCircle(
            color = indicatorColor,
            center = Offset(xOffset, indicatorSize.toPx() / 2),
            radius = indicatorSize.toPx() / 2
        )
    }

}