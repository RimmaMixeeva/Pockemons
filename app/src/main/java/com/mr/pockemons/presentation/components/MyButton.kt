package com.mr.pockemons.presentation.pockemon_info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.pockemons.presentation.navigation.Screens
import com.mr.pockemons.ui.theme.Orange

@Composable
fun MyButton(text: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Orange
    ) {
        Text(text = text,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable {
                    onClick()
                })
    }
}