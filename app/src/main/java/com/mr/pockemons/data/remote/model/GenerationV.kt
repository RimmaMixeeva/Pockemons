package com.mr.pockemons.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    val blackwhite: com.mr.pockemons.data.remote.model.BlackWhite
)