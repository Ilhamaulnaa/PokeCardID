package com.ilham.pokecardid.presentation.main.navigation

interface MainNavigator {

    fun navigateToDetailScreen()

}

object EmptyMainNavigator : MainNavigator{
    override fun navigateToDetailScreen() {}

}