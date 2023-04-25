package com.mr.pockemons.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mr.pockemons.R
import com.mr.pockemons.presentation.components.MyDialog
import com.mr.pockemons.presentation.navigation.SetUpNavGraph
import com.mr.pockemons.presentation.navigation.Wallpaper


class MainActivity : ComponentActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            MyDialog(mainViewModel)
            Wallpaper()
            navController = rememberNavController()
            SetUpNavGraph(navController = navController, mainViewModel)
        }
    }
}
