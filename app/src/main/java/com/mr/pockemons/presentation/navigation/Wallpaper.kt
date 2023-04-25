package com.mr.pockemons.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.mr.pockemons.R


@Composable
fun Wallpaper() {
    Image(
        painter = painterResource(id = R.drawable.pockemon), contentDescription = "background",
        modifier = Modifier
            .fillMaxSize()
            .scale(1.5f)
    )
}