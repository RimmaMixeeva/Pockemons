package com.mr.pockemons.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.mr.pockemons.PockemonApp
import com.mr.pockemons.data.remote.ApiInterface
import com.mr.pockemons.data.local.AppDatabase
import com.mr.pockemons.data.local.PockemonEntity
import com.mr.pockemons.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val application: PockemonApp,
                                        private val repository: DataRepository,
                                        private val pager: Pager<Int, PockemonEntity>
): ViewModel() {

    val beerPagingFlow = pager
        .flow
        .cachedIn(viewModelScope)

    var currentPockemonId: Int = 0

    var showDialog = MutableLiveData(false)

    fun fetchPockemonInfoById(): LiveData<PockemonEntity> {
        return  repository.readById(currentPockemonId)
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }

    fun getApplication(): PockemonApp {
        return application
    }
}