package com.ilham.pokecardid.presentation.main.navigation

interface MainNavigator {

    fun navigateToDetailScreen(id: String)

}

object EmptyMainNavigator : MainNavigator{
    override fun navigateToDetailScreen(id: String) {}

}