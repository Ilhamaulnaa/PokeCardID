package com.ilham.pokecardid.presentation.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ilham.pokecardid.navigation.composableWithSlideHorizontalAnimation
import com.ilham.pokecardid.presentation.home.HomeScreen
import com.ilham.pokecardid.presentation.main.component.BottomBar
import com.ilham.pokecardid.presentation.main.navigation.EmptyMainNavigator
import com.ilham.pokecardid.presentation.main.navigation.MainNavigator
import com.ilham.pokecardid.presentation.main.navigation.Screen
import com.ilham.pokecardid.presentation.profile.ProfileScreen
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    mainNavigator: MainNavigator
) {

    Scaffold(
        bottomBar = {
            BottomBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .background(Color.White.copy(alpha = 0.95f))
                    .shadow(4.dp, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                navController = navController
            )
        },
        modifier = modifier.padding(bottom = 20.dp)
    ){ paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding() - 48.dp)
        ) {
            composableWithSlideHorizontalAnimation(
                route = Screen.Home.route,
            ){
                HomeScreen(
                    navigateToDetailScreen = mainNavigator::navigateToDetailScreen
                )
            }
            composableWithSlideHorizontalAnimation(
                route = Screen.Profile.route
            ){
                ProfileScreen()
            }
        }
    }

}

@ExperimentalAnimationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Preview
@Composable
private fun MainScreenPreview() {
    PokeCardIDTheme {
        Surface {
            MainScreen(
                mainNavigator = EmptyMainNavigator
            )
        }
    }
}