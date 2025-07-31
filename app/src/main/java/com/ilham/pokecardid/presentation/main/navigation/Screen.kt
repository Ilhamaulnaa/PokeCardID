package com.ilham.pokecardid.presentation.main.navigation

sealed class Screen(val route: String){

    data object Home: Screen(route = "home")
    data object Profile: Screen(route = "profile")

}