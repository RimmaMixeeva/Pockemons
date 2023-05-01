package com.mr.pockemons.presentation.pockemon_info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.pockemons.presentation.MainViewModel
import com.mr.pockemons.presentation.components.PockemonImage
import com.mr.pockemons.presentation.navigation.Screens

import com.mr.pockemons.ui.theme.Orange


@Composable
fun PockemonInfoScreen(navController: NavController, viewModel: MainViewModel) {
    val pockemon = viewModel.getPockemonInfoById().observeAsState()
    Card(
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 80.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Orange,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Column {
            Text(
                text = pockemon.value?.name ?: "none",
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Card(
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                    .fillMaxSize(),
                elevation = 2.dp,
                backgroundColor = Color.White
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start, modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp)
                    ) {
                        Text(
                            text = "types: " + pockemon.value?.types,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "height: " + pockemon.value?.height.toString() + " cm",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "weight: " + pockemon.value?.weight?.toString() + " kg",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    PockemonImage(url = pockemon.value?.image ?: "", viewModel)

                    Card(
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 40.dp)
                            .fillMaxWidth(),
                        elevation = 2.dp,
                        backgroundColor = Orange
                    ) {
                    if (pockemon.value?.types == "failed to load") {
                        Text(text = "RELOAD",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .clickable {
                                    if (viewModel.isInternetAvailable()) {
                                        viewModel.launchJson(pockemon.value?.id, pockemon.value?.name)

                                    } else {
                                        viewModel.showDialog.value = true
                                    }
                                })
                    }}

                    Card(
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 40.dp)
                            .fillMaxWidth(),
                        elevation = 2.dp,
                        backgroundColor = Orange
                    ) {
                        Text(text = "BACK",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .clickable {
                                    navController.navigate(Screens.Main.route) {
                                        popUpTo(Screens.Main.route) {
                                            inclusive = true
                                        }
                                    }
                                })
                    }
                }


            }
        }

    }
}