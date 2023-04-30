package com.mr.pockemons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.mr.pockemons.presentation.MainViewModel
import com.mr.pockemons.presentation.components.MyDialog
//import com.mr.pockemons.presentation.components.MyDialog
import com.mr.pockemons.presentation.navigation.SetUpNavGraph
import com.mr.pockemons.presentation.navigation.Wallpaper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MyDialog(mainViewModel)
            Wallpaper()
            navController = rememberNavController()

            SetUpNavGraph(navController = navController, mainViewModel)
        }
    }
}
