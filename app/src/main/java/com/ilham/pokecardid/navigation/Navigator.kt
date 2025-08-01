package com.ilham.pokecardid.navigation

import androidx.navigation.NavHostController
import com.ilham.pokecardid.presentation.main.navigation.MainNavigator

class Navigator(
    private val navController: NavHostController
): MainNavigator {
    override fun navigateToDetailScreen(id: String) { navController.navigateToDetailScreen(id)}

}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToDetailScreen(id: String,) {
    navigate(Screen.Detail.generateRoute(id))
}
