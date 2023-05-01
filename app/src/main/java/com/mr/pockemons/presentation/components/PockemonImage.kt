package com.mr.pockemons.presentation.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.*
import coil.request.ImageRequest
import com.mr.pockemons.R
import com.mr.pockemons.presentation.MainViewModel

@Composable
fun PockemonImage(url: String, viewModel: MainViewModel) {
    var hasNet by remember {
        mutableStateOf(viewModel.isInternetAvailable(viewModel.getApplication()))
    }
    var imageWasLoaded by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .clip(CircleShape)
            .clickable {
                hasNet = viewModel.isInternetAvailable(viewModel.getApplication())
                if (!hasNet && !imageWasLoaded) {
                    viewModel.showDialog.value = true
                }
            }
    ) {
        var painter: AsyncImagePainter
        if (hasNet) {
            painter =
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = url).build()
                )
        } else {
            painter =
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = url).build(),
                )
        }
        val painterState = painter.state

        Image(
            painter = painter,
            contentDescription = "Pockemon Image",
            modifier = Modifier.fillMaxSize()
        )

        if (painterState is AsyncImagePainter.State.Loading || painterState is AsyncImagePainter.State.Error) {
            GifImage(R.drawable.loading)
        } else {
            imageWasLoaded = true
        }

    }
}
