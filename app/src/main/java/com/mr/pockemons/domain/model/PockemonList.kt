package com.mr.pockemons.domain.model

data class PockemonList(
val count: Int,
val next: String?,
val previous: String?,
val results: List<PockemonListEntry>
)