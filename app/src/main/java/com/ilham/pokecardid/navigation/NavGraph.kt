package com.ilham.pokecardid.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.ilham.pokecardid.presentation.auth.login.LoginScreen
import com.ilham.pokecardid.presentation.auth.register.RegisterScreen
import com.ilham.pokecardid.presentation.detail.DetailScreen
import com.ilham.pokecardid.presentation.detail.DetailViewModel
import com.ilham.pokecardid.presentation.main.MainScreen
import java.util.Locale

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun NavGraph(
//    startDestination: String = Screen.Main.route
    startDestination: String = Screen.Login.route
) {

    val navController = rememberNavController()
    val mainNavigator = Navigator(navController)

    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composableWithSlideHorizontalAnimation(route = Screen.Login.route){
            LoginScreen(
                navigateToRegisterScreen = navController::navigateToRegisterScreen,
                navigateToMainScreen = mainNavigator::navigateToMainScreen
            )
        }
        composableWithSlideHorizontalAnimation(route = Screen.Register.route){
            RegisterScreen(
                navigateToLoginScreen = navController::navigateToLoginScreen
            )
        }
        composableWithSlideHorizontalAnimation(route = Screen.Main.route){
            MainScreen(
                mainNavigator = mainNavigator
            )
        }
        composableWithSlideHorizontalAnimation(
            route = "detail/{pokemon_id}",
            arguments = listOf(navArgument("pokemon_id"){
                    type = NavType.StringType
                }
            )
        ){ backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString(Screen.POKEMON_ID) ?: ""
            DetailScreen(
                pokemonName = pokemonName,
                navigateUp = navController::navigateUp
            )
        }
    }
}