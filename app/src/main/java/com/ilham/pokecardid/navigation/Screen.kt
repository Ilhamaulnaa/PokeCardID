package com.ilham.pokecardid.navigation

sealed class Screen(val route: String) {

    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Main : Screen("main")

}