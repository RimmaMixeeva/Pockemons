package com.mr.pockemons.presentation.components

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mr.pockemons.presentation.MainViewModel
import com.mr.pockemons.ui.theme.Orange



@Composable
fun MyDialog(viewModel: MainViewModel) {
    var opened =  viewModel.showDialog.observeAsState()
    val context = LocalContext.current
    if (opened.value == true) {
        AlertDialog(
            onDismissRequest = {
                                 viewModel.showDialog.value = false
                               },
            title = {
                Text(text = "Ooops. Network Error")
            },
            text = {
                Text("Turn on the WIFI and try again")
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.showDialog.value = false
                        context.startActivity(
                            Intent(Settings.ACTION_WIFI_SETTINGS)
                        )
                    },
                    modifier = Modifier.padding(end = 40.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange)
                ) {
                    Text(text = "TURN ON", color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        viewModel.showDialog.value = false
                    },
                    modifier = Modifier.padding(end = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange)
                ) {
                    Text(text = "NOT NOW", color = Color.White)
                }
            }
        )
    }

}