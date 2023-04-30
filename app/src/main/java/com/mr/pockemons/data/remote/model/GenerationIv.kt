package com.mr.pockemons.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenerationIv(
    @SerializedName("diamond-pearl")
    val diamondpearl: com.mr.pockemons.data.remote.model.DiamondPearl,
    @SerializedName("heartgold-soulsilver")
    val heartgoldsoulsilver: com.mr.pockemons.data.remote.model.HeartgoldSoulsilver,
    val platinum: com.mr.pockemons.data.remote.model.Platinum
)