package com.mr.pockemons.presentation.navigation

sealed class Screens(val route: String) {
    object Main : Screens(route = "list_screen")
    object Pockemon : Screens(route = "pockemon_screen")
}