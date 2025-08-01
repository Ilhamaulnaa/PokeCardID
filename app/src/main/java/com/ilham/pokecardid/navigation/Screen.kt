package com.ilham.pokecardid.navigation

sealed class Screen(val route: String) {

    companion object {
        const val POKEMON_ID = "pokemon_id"
    }

    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Main : Screen("main")

    data object Detail : Screen(route = "detail/{$POKEMON_ID}") {
        fun generateRoute(pokemonId: String): String {
            return "detail/$pokemonId"
        }
    }

}