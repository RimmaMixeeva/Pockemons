package com.mr.pockemons.data.mappers

import com.mr.pockemons.data.local.PockemonEntity
import com.mr.pockemons.data.remote.model.Pockemon
import com.mr.pockemons.data.remote.model.PockemonListEntry

fun Pockemon.toPockemonEntity(): PockemonEntity {
    var  tmpId = this.id
    return PockemonEntity(
        id = if(tmpId > 10000) tmpId - 8990 else tmpId,
        name = this.name,
        image = this.sprites.front_default,
        types = this.types.map { item -> item.type.name }.joinToString(", "),
        weight = this.weight,
        height = this.height
    )
}

fun PockemonListEntry.toPockemonEntity(): PockemonEntity {
    var tmpId = this.url.removePrefix("https://pokeapi.co/api/v2/pokemon/").dropLast(1).toInt()
    return PockemonEntity(
        id = if(tmpId > 10000) tmpId - 8990 else tmpId ,
        name = this.name,
        image = "none",
        types = "none",
        weight = 0,
        height = 0
    )
}