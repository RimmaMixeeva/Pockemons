package com.mr.pockemons.data.API

import com.mr.pockemons.domain.model.others.PockemonList
import com.mr.pockemons.domain.model.others.Pockemon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("api/v2/pokemon/")
    suspend fun getPockemons() : PockemonList

    @GET("api/v2/pokemon/{id}")
    suspend fun getPockemonById(@Path("id") id: Int) : Pockemon

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