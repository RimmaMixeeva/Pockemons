package com.mr.pockemons.data.remote.model

import com.google.gson.annotations.SerializedName

data class Versions(
    @SerializedName("generation-i")
    val generationi: com.mr.pockemons.data.remote.model.GenerationI,
    @SerializedName("generation-ii")
    val generationii: com.mr.pockemons.data.remote.model.GenerationIi,
    @SerializedName("generation-iii")
    val generationiii: com.mr.pockemons.data.remote.model.GenerationIii,
    @SerializedName("generation-iv")
    val generationiv: com.mr.pockemons.data.remote.model.GenerationIv,
    @SerializedName("generation-v")
    val generationv: com.mr.pockemons.data.remote.model.GenerationV,
    @SerializedName("generation-vi")
    val generationvi: com.mr.pockemons.data.remote.model.GenerationVi,
    @SerializedName("generation-vii")
    val generationvii: com.mr.pockemons.data.remote.model.GenerationVii,
    @SerializedName("generation-viii")
    val generationviii: com.mr.pockemons.data.remote.model.GenerationViii
)