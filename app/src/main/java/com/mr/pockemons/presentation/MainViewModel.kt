package com.mr.pockemons.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.pockemons.data.API.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val apiInterface = ApiInterface.create()

    init {
        Log.i("presentation", "MainViewModel created")
        launchJson()
    }
    fun launchJson() {
        viewModelScope.launch {
            val product = apiInterface.getPockemons()
            val pockemon = apiInterface.getPockemonById(1)
            Log.i("Aaaaaaaa", product.toString())
            Log.i("Aaaaaaaa", pockemon.toString())
        }
    }
}