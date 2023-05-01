package com.mr.pockemons.presentation


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.mr.pockemons.PockemonApp
import com.mr.pockemons.data.remote.ApiInterface
import com.mr.pockemons.data.local.AppDatabase
import com.mr.pockemons.data.local.PockemonEntity
import com.mr.pockemons.data.mappers.toFetchId
import com.mr.pockemons.data.mappers.toPockemonEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiInterface: ApiInterface,
    private val application: PockemonApp,
    private val database: AppDatabase,
    pager: Pager<Int, PockemonEntity>,
) : ViewModel() {

    var scrollPosition = 0
    val beerPagingFlow = pager
        .flow
        .cachedIn(viewModelScope)

    var currentPockemonId: Int = 0

    var showDialog = MutableLiveData(false)

    fun getPockemonInfoById(): LiveData<PockemonEntity> {
        return database.pockemonDao().getPockemonById(currentPockemonId)
    }

    fun isInternetAvailable (): Boolean {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }

    fun launchJson(id: Int?, name: String?) {
        viewModelScope.launch {
            if (id!= null && name != null) {
            val pockemon =  try {
               apiInterface.getPockemonById(id.toFetchId()).toPockemonEntity()
            } catch (e: Exception) {
                PockemonEntity.failedPockemon(id, name)
            }
            database.pockemonDao().upsertAll(listOf(pockemon))
            }
        }
    }
}