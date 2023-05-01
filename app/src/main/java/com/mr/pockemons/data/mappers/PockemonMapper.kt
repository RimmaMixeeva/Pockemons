package com.mr.pockemons.data.mappers

import com.mr.pockemons.data.local.PockemonEntity
import com.mr.pockemons.data.remote.model.Pockemon
import com.mr.pockemons.data.remote.model.PockemonListEntry

fun Pockemon.toPockemonEntity(): PockemonEntity {
    return PockemonEntity(
        id = this.id.toDbId(),
        name = this.name,
        image = this.sprites.front_default.toString(),
        types = this.types.map { item -> item.type.name }.joinToString(", "),
        weight = this.weight,
        height = this.height
    )
}

fun PockemonListEntry.toPockemonEntity(): PockemonEntity {
    val tmpId = this.url.removePrefix("https://pokeapi.co/api/v2/pokemon/").dropLast(1).toInt()
    return PockemonEntity(
        id = tmpId.toDbId(),
        name = this.name,
        image = "none",
        types = "none",
        weight = 0,
        height = 0
    )
}


