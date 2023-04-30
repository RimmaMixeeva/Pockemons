package com.mr.pockemons.data.remote

import com.mr.pockemons.data.remote.model.Pockemon
import com.mr.pockemons.data.remote.model.PockemonList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/v2/pokemon/")
    suspend fun getPockemons() : PockemonList

    @GET("api/v2/pokemon/")
    suspend fun getPockemonsPage(@Query("offset") offset: Int, @Query("limit") limit: Int = 20) : PockemonList

    @GET("api/v2/pokemon/{id}")
    suspend fun getPockemonById(@Path("id") id: Int) : Pockemon

    companion object {

        var BASE_URL = "https://pokeapi.co/"
    }
}