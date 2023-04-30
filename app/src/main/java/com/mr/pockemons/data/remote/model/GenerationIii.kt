package com.mr.pockemons.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenerationIii(
    val emerald: com.mr.pockemons.data.remote.model.Emerald,
    @SerializedName("firered-leafgreen")
    val fireredleafgreen: com.mr.pockemons.data.remote.model.FireredLeafgreen,
    @SerializedName("ruby-sapphire")
    val rubysapphire: com.mr.pockemons.data.remote.model.RubySapphire
)