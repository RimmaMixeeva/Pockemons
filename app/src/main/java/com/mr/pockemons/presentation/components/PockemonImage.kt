package com.mr.pockemons.presentation.components

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.*
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.mr.pockemons.R
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
            .clip(CircleShape)
            .clickable {
                hasNet = viewModel.isInternetAvailable(viewModel.getApplication())
            }
    ) {
        var painter: AsyncImagePainter
        if(hasNet) {
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

        Image(painter = painter, contentDescription = "Pockemon Image",  modifier = Modifier.fillMaxSize())

        if(painterState is AsyncImagePainter.State.Loading){
            GifImage()
        }

        if(painterState is AsyncImagePainter.State.Error){
            GifImage()
        }

    }
}
