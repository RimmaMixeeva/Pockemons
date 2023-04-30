package com.mr.pockemons.presentation.pockemons_list


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
//import androidx.paging.LoadState
//import androidx.paging.compose.LazyPagingItems
//import androidx.ui.core.Alignment
import androidx.ui.layout.Align
import com.mr.pockemons.data.local.PockemonEntity
import com.mr.pockemons.presentation.MainViewModel
import com.mr.pockemons.presentation.components.MyDialog
import com.mr.pockemons.presentation.navigation.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items



@Composable
fun PockemonListScreen(navController: NavController, viewModel: MainViewModel) {

    var pockemons = viewModel.beerPagingFlow.collectAsLazyPagingItems()

    var checkTheNet by remember {
        mutableStateOf(viewModel.isInternetAvailable(viewModel.getApplication()))
    }
    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(isLoading) {
        if(isLoading) {
            delay(3000)
            isLoading = false
        }
    }
    LaunchedEffect(checkTheNet) {
        if(checkTheNet) {
            pockemons.refresh()
        }
    }


    if (pockemons.itemSnapshotList.size == 0) {

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
        Box(modifier = Modifier.fillMaxSize()){
            if(pockemons.loadState.refresh is LoadState.Loading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }else{
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    items(pockemons){ item ->
                        if(item != null){
                            Card(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 8.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                         viewModel.currentPockemonId = item.id
                                         viewModel.fetchPockemonInfoById()
                                         navController.navigate(Screens.Pockemon.route)
                                    },
                                elevation = 2.dp,
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(corner = CornerSize(16.dp))
                            ) {
                                Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)) {
                                    Text(text = item.id.toString() + " " + item.name, style = typography.h5)
                                }
                            }

                        }
                    }
                    item {
                        if(pockemons.loadState.append is LoadState.Loading){
                            CircularProgressIndicator()
                        }
                    }
                }
            }

        }
}
}