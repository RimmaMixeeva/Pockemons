package com.mr.pockemons.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire")
    val omegarubyalphasapphire: com.mr.pockemons.data.remote.model.OmegarubyAlphasapphire,
    @SerializedName("x-y")
    val xy: com.mr.pockemons.data.remote.model.XY
)