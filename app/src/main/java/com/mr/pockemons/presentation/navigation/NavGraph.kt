package com.mr.pockemons.presentation.navigation

import android.transition.Slide
import android.transition.Transition
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mr.pockemons.presentation.MainViewModel
import com.mr.pockemons.presentation.pockemon_info.PockemonInfoScreen
import com.mr.pockemons.presentation.pockemons_list.PockemonListScreen
import java.time.zone.ZoneOffsetTransition

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel,
) {
  NavHost(navController = navController , startDestination = Screens.Main.route) {
      composable(
          route = Screens.Main.route
      ) {
          PockemonListScreen(navController, viewModel)
      }
      composable(
          route = Screens.Pockemon.route
      ) {
         PockemonInfoScreen(navController, viewModel)
      }
  }
}