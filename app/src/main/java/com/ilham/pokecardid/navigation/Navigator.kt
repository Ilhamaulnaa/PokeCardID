package com.ilham.pokecardid.navigation

import androidx.navigation.NavHostController
import com.ilham.pokecardid.presentation.main.navigation.MainNavigator

class Navigator(
    private val navController: NavHostController
): MainNavigator {

    override fun navigateToLoginScreen() { navController.navigateToLoginScreen() }
    override fun navigateToRegisterScreen() { navController.navigateToRegisterScreen() }
    override fun navigateToMainScreen() { navController.navigateToMainScreen() }
    override fun navigateToDetailScreen(id: String) { navController.navigateToDetailScreen(id)}

}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToLoginScreen(
    clearBackStack: Boolean = true,
    from: Screen = Screen.Main
) {
    navigate(Screen.Login.route){
        if (!clearBackStack) return@navigate
        popUpTo(from.route){
            inclusive = true
        }
    }
}
@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToRegisterScreen() {
    navigate(Screen.Register.route){}
}
@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToMainScreen(
    clearBackStack: Boolean = true
) {
    navigate(Screen.Main.route){
        if (clearBackStack) {
            popUpTo(0) { inclusive = true }
        }
        launchSingleTop = true
    }
}
@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToDetailScreen(id: String,) {
    navigate(Screen.Detail.generateRoute(id))
}
