package com.mr.pockemons.data.remote.model

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: com.mr.pockemons.data.remote.model.MoveLearnMethod,
    val version_group: com.mr.pockemons.data.remote.model.VersionGroup
)