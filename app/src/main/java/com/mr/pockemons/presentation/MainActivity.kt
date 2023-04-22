package com.mr.pockemons.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.mr.pockemons.data.API.ApiInterface
import com.mr.pockemons.domain.model.PockemonList
import com.mr.pockemons.ui.theme.PockemonsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        var list: List<PockemonList>

        super.onCreate(savedInstanceState)
        Log.i("presentation", "Activity created")
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val apiInterface = ApiInterface.create()
        CoroutineScope(Dispatchers.IO).launch {
            val product = apiInterface.getPockemons()
            Log.i("Aaaaaaaa", product.toString())
        }

        setContent {
            PockemonsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PockemonsTheme {
        Greeting("Android")
    }
}