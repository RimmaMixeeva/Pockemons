package com.mr.pockemons.presentation.pockemons_list


import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.ui.core.Alignment
import androidx.ui.layout.Align
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
    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(isLoading) {
        Log.i("CHECH LOADING",isLoading.toString())
        if(isLoading) {
            delay(3000)
            isLoading = false
        }
    }
    LaunchedEffect(checkTheNet) {
        if(checkTheNet) {
            viewModel.launchJson()
        }
    }


    if (pockemonList.value.isEmpty()) {

        if (isLoading) {
            Text(text = "LOADING...", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White,
            modifier = Modifier.fillMaxSize().padding(top = 120.dp), textAlign = TextAlign.Center)
        } else {
            Text(text = "TAP TO RELOAD" , modifier = Modifier.fillMaxSize().clickable {
                isLoading = true
                checkTheNet = viewModel.isInternetAvailable(viewModel.getApplication())
            }.padding(top = 120.dp), fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White,
            textAlign = TextAlign.Center)
        }

        if (!checkTheNet) {
           LaunchedEffect(Unit) {
               delay(3000)
               viewModel.showDialog.value = true
           }
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