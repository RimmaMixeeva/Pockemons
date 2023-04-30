package com.mr.pockemons.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue")
    val redblue: com.mr.pockemons.data.remote.model.RedBlue,
    val yellow: com.mr.pockemons.data.remote.model.Yellow
)