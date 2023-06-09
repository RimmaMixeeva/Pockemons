package com.mr.pockemons.data.remote.model

data class Pockemon(
    val abilities: List<Ability>,
    val base_experience: Any,
    val forms: List<Form>,
    val game_indices: List<Any>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)