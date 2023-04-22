package com.mr.pockemons.data.API

import com.mr.pockemons.domain.model.PockemonList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/v2/pokemon/")
    suspend fun getPockemons() : PockemonList

    companion object {

        var BASE_URL = "https://pokeapi.co/"

        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}