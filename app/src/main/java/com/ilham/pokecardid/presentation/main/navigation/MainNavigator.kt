package com.ilham.pokecardid.presentation.main.navigation

interface MainNavigator {

    fun navigateToLoginScreen()
    fun navigateToRegisterScreen()
    fun navigateToMainScreen()
    fun navigateToDetailScreen(id: String)

}

object EmptyMainNavigator : MainNavigator{

    override fun navigateToMainScreen() { }
    override fun navigateToLoginScreen() { }
    override fun navigateToRegisterScreen() {}
    override fun navigateToDetailScreen(id: String) {}

}