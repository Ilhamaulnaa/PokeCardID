package com.ilham.pokecardid.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@ExperimentalAnimationApi
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    startDestination: String = ""
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composableWithSlideHorizontalAnimation(route = Screen.Login.route){

        }
        composableWithSlideHorizontalAnimation(route = Screen.Register.route){

        }
        composableWithSlideHorizontalAnimation(route = Screen.Main.route){

        }
    }
}