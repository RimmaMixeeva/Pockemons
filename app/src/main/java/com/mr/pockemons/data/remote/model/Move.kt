package com.mr.pockemons.data.remote.model

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)