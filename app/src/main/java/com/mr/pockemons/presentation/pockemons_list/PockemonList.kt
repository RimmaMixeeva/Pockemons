package com.mr.pockemons.presentation.pockemons_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.mr.pockemons.data.local.PockemonEntity
import com.mr.pockemons.presentation.MainViewModel
import com.mr.pockemons.presentation.navigation.Screens


@Composable
fun PockemonList(pockemons: LazyPagingItems<PockemonEntity>, navController: NavController, viewModel: MainViewModel) {

    val lazyListState = rememberLazyListState()
    LaunchedEffect(Unit) {
        lazyListState.scrollToItem(viewModel.scrollPosition)
    }
    LaunchedEffect(lazyListState.firstVisibleItemIndex) {
        viewModel.scrollPosition = lazyListState.firstVisibleItemIndex
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = lazyListState
    ) {
        items(pockemons) { item ->
            if (item != null) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .fillMaxSize()
                        .clickable {
                            viewModel.currentPockemonId = item.id
                            navController.navigate(Screens.Pockemon.route)
                        },
                    elevation = 2.dp,
                    backgroundColor = Color.White
                ) {
                    Row(
                        modifier = Modifier.padding(
                            vertical = 10.dp,
                            horizontal = 10.dp
                        )
                    ) {
                        Text(
                            text = item.id.toString() + " " + item.name,
                            style = MaterialTheme.typography.h5
                        )
                    }
                }

            }
        }
        item {
            if (pockemons.loadState.append is LoadState.Loading) {
                CircularProgressIndicator()
            }
        }
    }
}
