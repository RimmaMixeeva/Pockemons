package com.mr.pockemons.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mr.pockemons.data.API.ApiInterface
import com.mr.pockemons.data.data_source.AppDatabase
import com.mr.pockemons.data.repository.PockemonRepository
import com.mr.pockemons.domain.model.main.PockemonEntity
import kotlinx.coroutines.launch


class MainViewModel(application: Application): AndroidViewModel(application) {
    var currentPockemon = 0
    val apiInterface = ApiInterface.create()
    private val repository: PockemonRepository

    var pockemonsLiveData: LiveData<List<PockemonEntity>>
    val showDialog = MutableLiveData<Boolean>(false)

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
            val pockemonList = apiInterface.getPockemons().results
            pockemonList.forEachIndexed {
                    index, pockemon ->
                var info = apiInterface.getPockemonById(
                    pockemon.url.removePrefix("https://pokeapi.co/api/v2/pokemon/").dropLast(1).toInt()
                )
                repository.upsert(PockemonEntity(index+1, pockemon.name, info.sprites.front_default, info.types.map{item -> item.type.name}.joinToString(", "), info.weight, info.height))
            }
        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }
}