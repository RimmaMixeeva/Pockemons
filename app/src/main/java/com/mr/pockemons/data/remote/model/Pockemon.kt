package com.mr.pockemons.data.remote.model

data class Pockemon(
    val abilities: List<com.mr.pockemons.data.remote.model.Ability>,
    val base_experience: Int,
    val forms: List<com.mr.pockemons.data.remote.model.Form>,
    val game_indices: List<com.mr.pockemons.data.remote.model.GameIndice>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<com.mr.pockemons.data.remote.model.Move>,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: com.mr.pockemons.data.remote.model.Species,
    val sprites: com.mr.pockemons.data.remote.model.Sprites,
    val stats: List<com.mr.pockemons.data.remote.model.Stat>,
    val types: List<com.mr.pockemons.data.remote.model.Type>,
    val weight: Int
)