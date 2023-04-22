package com.mr.pockemons.domain.model.others

import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    val blackwhite: BlackWhite
)