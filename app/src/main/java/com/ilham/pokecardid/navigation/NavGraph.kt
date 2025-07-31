package com.ilham.pokecardid.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ilham.pokecardid.presentation.auth.login.LoginScreen
import com.ilham.pokecardid.presentation.auth.register.RegisterScreen
import com.ilham.pokecardid.presentation.main.MainScreen

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun NavGraph(
    startDestination: String = Screen.Login.route
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composableWithSlideHorizontalAnimation(route = Screen.Login.route){
            LoginScreen()
        }
        composableWithSlideHorizontalAnimation(route = Screen.Register.route){
            RegisterScreen()
        }
        composableWithSlideHorizontalAnimation(route = Screen.Main.route){
            MainScreen()
        }
    }
}