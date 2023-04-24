package com.mr.pockemons.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.*
import com.mr.pockemons.data.API.ApiInterface
import com.mr.pockemons.data.data_source.AppDatabase
import com.mr.pockemons.data.repository.PockemonRepository
import com.mr.pockemons.domain.model.main.PockemonEntity
import kotlinx.coroutines.*

class MainViewModel(application: Application): AndroidViewModel(application) {
    var currentPockemon = 0
    val apiInterface = ApiInterface.create()
    private val repository: PockemonRepository
    lateinit var pockemonsLiveData: LiveData<List<PockemonEntity>>

    fun fetchAllPockemons():  LiveData<List<PockemonEntity>> {
       return repository.readAllData
    }
    fun fetchPockemonInfoById(): LiveData<PockemonEntity> {
            return repository.readById(currentPockemon)
    }

    init {
        val pockemonDao = AppDatabase.getDbInstance(application).pockemonDao()
        repository = PockemonRepository(pockemonDao)
        pockemonsLiveData = repository.readAllData
        if (isInternetAvailable(application)) {
        launchJson()
         }
    }

    fun launchJson() {
        viewModelScope.launch {
            delay(5000)
            if (pockemonsLiveData.value == null) {
            val pockemonList = apiInterface.getPockemons().results
            pockemonList.forEachIndexed {
                    index, pockemon ->
                var info = apiInterface.getPockemonById(
                    pockemon.url.removePrefix("https://pokeapi.co/api/v2/pokemon/").dropLast(1).toInt()
                )
                repository.upsert(PockemonEntity(index+1, pockemon.name, info.sprites.front_default, info.types.joinToString(separator = ", "), info.weight, info.height))
            }
        }
        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}