package com.mr.pockemons.presentation.pockemons_list


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.mr.pockemons.presentation.MainViewModel
import com.mr.pockemons.presentation.components.MyDialog
import com.mr.pockemons.presentation.navigation.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun PockemonListScreen(navController: NavController, viewModel: MainViewModel) {

    var pockemonList = viewModel.fetchAllPockemons().observeAsState(arrayListOf())
    var checkTheNet by remember {
        mutableStateOf(viewModel.isInternetAvailable(viewModel.getApplication()))
    }
    if (pockemonList.value.isEmpty()) {
                Text(text = "LOADING..." )
        if (!checkTheNet) {
           LaunchedEffect(Unit) {
               delay(3000)
               viewModel.showDialog.value = true
           }
        } else {
            viewModel.launchJson()
        }
    }
    else {
    LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
        itemsIndexed(
            pockemonList.value
        )
        { index, item ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        viewModel.currentPockemon = item.id
                        navController.navigate(Screens.Pockemon.route)
                    },
                elevation = 2.dp,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(corner = CornerSize(16.dp))
            ) {
                Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)) {
                     Text(text = item.name, style = typography.h5)
                }
            }
        }
    }
}
}