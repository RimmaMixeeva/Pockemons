package com.mr.pockemons.presentation.pockemon_info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavController
import com.mr.pockemons.domain.model.main.PockemonEntity
import com.mr.pockemons.domain.model.others.Pockemon
import com.mr.pockemons.presentation.MainViewModel
import com.mr.pockemons.presentation.navigation.Screens

@Composable
fun PockemonInfoScreen(navController: NavController, viewModel: MainViewModel) {

    Column() {
        Text(text = "BACK", modifier = Modifier.clickable {
            navController.navigate(Screens.Main.route) {
                popUpTo(Screens.Main.route) {
                    inclusive = true
                }
        }
        })

          Text(text = "name")
//        Text(text = pockemon.types)
//        Text(text = pockemon.weight.toString())
//        Text(text = pockemon.height.toString())


}
}