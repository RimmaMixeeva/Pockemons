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
    val apiInterface = ApiInterface.create()
    private val repository: PockemonRepository
    lateinit var pockemonsLiveData: LiveData<List<PockemonEntity>>
    fun fetchAllPockemons():  LiveData<List<PockemonEntity>> {
       return repository.readAllData
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

            Log.i("pockemonList", pockemonList.toString())
            pockemonList.forEachIndexed {
                    index, pockemon -> repository.upsert(PockemonEntity(index+1, pockemon.name, "null", "null", 0, 0))
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