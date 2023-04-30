package com.mr.pockemons.data.remote.model

data class Move(
    val move: com.mr.pockemons.data.remote.model.MoveX,
    val version_group_details: List<com.mr.pockemons.data.remote.model.VersionGroupDetail>
)