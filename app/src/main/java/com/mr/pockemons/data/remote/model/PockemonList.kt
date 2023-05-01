package com.mr.pockemons.data.remote.model

data class PockemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PockemonListEntry>
)