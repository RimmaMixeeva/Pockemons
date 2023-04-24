package com.mr.pockemons.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.*
import coil.request.ImageRequest
import com.mr.pockemons.presentation.MainViewModel

@Composable
fun PockemonImage(url: String, viewModel: MainViewModel) {
    var hasNet by remember {
        mutableStateOf(viewModel.isInternetAvailable(viewModel.getApplication()))
    }
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .background(Color.Green)
            .clickable {
                hasNet = viewModel.isInternetAvailable(viewModel.getApplication())}
    ) {
        if(hasNet) {
        val painter =
            rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = url).build()
            )
        val painterState = painter.state

        Image(painter = painter, contentDescription = "Pockemon Image",  modifier = Modifier.fillMaxSize())

        if(painterState is AsyncImagePainter.State.Loading){
            CircularProgressIndicator(modifier = Modifier.scale(2f))
        }
        if(painterState is AsyncImagePainter.State.Error){

        }
    }
    }
}