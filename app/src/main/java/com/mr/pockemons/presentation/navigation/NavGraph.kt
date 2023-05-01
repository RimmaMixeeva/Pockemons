package com.mr.pockemons.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mr.pockemons.presentation.MainViewModel
import com.mr.pockemons.presentation.pockemon_info.PockemonInfoScreen
import com.mr.pockemons.presentation.pockemons_list.PockemonListScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    NavHost(navController = navController, startDestination = Screens.Main.route) {
        composable(
            route = Screens.Main.route
        ) {
            PockemonListScreen(navController, mainViewModel)
        }
        composable(
            route = Screens.Pockemon.route
        ) {
            PockemonInfoScreen(navController, mainViewModel)
        }
    }
}