package com.mr.pockemons.presentation.pockemons_list


import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mr.pockemons.presentation.MainViewModel
import com.mr.pockemons.presentation.navigation.Screens
import kotlinx.coroutines.delay
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items


@Composable
fun PockemonListScreen(navController: NavController, viewModel: MainViewModel) {

    var pockemons = viewModel.beerPagingFlow.collectAsLazyPagingItems()

    var checkTheNet by remember {
        mutableStateOf(viewModel.isInternetAvailable())
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(3000)
            isLoading = false
        }
    }
    LaunchedEffect(checkTheNet) {
        if (checkTheNet) {
            pockemons.refresh()
        }
    }


    if (pockemons.itemSnapshotList.size == 0) {

        if (isLoading) {
            Text(
                text = "LOADING...",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 120.dp),
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                text = "TAP TO RELOAD",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        isLoading = true
                        checkTheNet = viewModel.isInternetAvailable()
                    }
                    .padding(top = 120.dp),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        if (!checkTheNet) {
            LaunchedEffect(Unit) {
                delay(3000)
                viewModel.showDialog.value = true
            }
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                "RELOAD",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .clickable {
                        checkTheNet = viewModel.isInternetAvailable()
                        if (checkTheNet) {
                            pockemons.refresh()
                        } else {
                            viewModel.showDialog.value = true
                        }
                    },
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Box(modifier = Modifier.fillMaxSize()) {
                if (pockemons.loadState.refresh is LoadState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    PockemonList(pockemons = pockemons, navController = navController, viewModel = viewModel)
                }

            }
        }
    }
}
