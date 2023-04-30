package com.mr.pockemons.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenerationVii(
    val icons: com.mr.pockemons.data.remote.model.Icons,
    @SerializedName("ultra-sun-ultra-moon")
    val ultrasunultramoon: com.mr.pockemons.data.remote.model.UltraSunUltraMoon
)