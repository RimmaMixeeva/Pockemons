package com.mr.pockemons.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mr.pockemons.presentation.navigation.SetUpNavGraph


class MainActivity : ComponentActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            navController = rememberNavController()
            SetUpNavGraph(navController = navController, mainViewModel)
        }
    }
}
