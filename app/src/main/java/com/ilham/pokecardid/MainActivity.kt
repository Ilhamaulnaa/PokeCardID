package com.ilham.pokecardid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ilham.pokecardid.navigation.NavGraph
import com.ilham.pokecardid.presentation.main.MainScreen
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeCardIDTheme {

                MainScreen()

//                val navController = rememberNavController()
//                NavHost(
//                    navController = navController,
//                    startDestination = "pokemon_list_screen"
//                ) {
//                    composable(
//                        route = "pokemon_list-screen"
//                    ){
//
//                    }
//                    composable(
//                        route = "pokemon_detail_screen/{dominantColor}/{pokemonName}",
//                        arguments = listOf(
//                            navArgument("dominantColor"){
//                                type = NavType.IntType
//                            },
//                            navArgument("pokemonName"){
//                                type = NavType.StringType
//                            }
//                        )
//                    ){
//                        val dominantColor = remember {
//                            val color = it.arguments?.getInt("dominantColor")
//                            color?.let { Color(it) } ?: Color.White
//                        }
//                        val pokemonName = remember {
//                            it.arguments?.getString("pokemonName")
//                        }
//                    }
//                }
            }
        }
    }
}
