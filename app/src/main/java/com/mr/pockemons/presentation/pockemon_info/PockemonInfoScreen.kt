package com.mr.pockemons.presentation.pockemon_info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.mr.pockemons.presentation.MainViewModel
import com.mr.pockemons.presentation.components.PockemonImage
import com.mr.pockemons.presentation.navigation.Screens

@Composable
fun PockemonInfoScreen(navController: NavController, viewModel: MainViewModel) {

    val pockemon = viewModel.fetchPockemonInfoById().observeAsState()
    Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
        Text(text = "BACK", modifier = Modifier.clickable {
            navController.navigate(Screens.Main.route) {
                popUpTo(Screens.Main.route) {
                    inclusive = true
                }
        }
        })

        Text(text =  pockemon.value?.name?:"")
        Text(text =  pockemon.value?.types?:"")
        Text(text =  pockemon.value?.height.toString())
        Text(text =  pockemon.value?.weight?.toString()?:"")
        PockemonImage(url = pockemon.value?.image ?: "", viewModel)
    }
}

@Preview
@Composable
fun PockemonInfoScreen() {
    PockemonInfoScreen()
}