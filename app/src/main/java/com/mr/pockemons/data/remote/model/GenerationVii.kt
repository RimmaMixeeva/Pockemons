package com.mr.pockemons.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenerationVii(
    val icons: Icons,
    @SerializedName("ultra-sun-ultra-moon")
    val ultrasunultramoon: UltraSunUltraMoon
)