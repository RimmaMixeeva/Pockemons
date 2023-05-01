package com.mr.pockemons.data.remote.model

data class PockemonList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PockemonListEntry>
)