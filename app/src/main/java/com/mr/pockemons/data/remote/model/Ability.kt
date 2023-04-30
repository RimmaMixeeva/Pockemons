package com.mr.pockemons.data.remote.model

data class Ability(
    val ability: com.mr.pockemons.data.remote.model.AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)