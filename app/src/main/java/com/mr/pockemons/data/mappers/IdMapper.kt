package com.mr.pockemons.data.mappers

fun Int.toFetchId(): Int {
    if (this > 1010) return this + 8990 else return this
}

fun Int.toDbId(): Int {
    if (this > 10000) return this - 8990 else return this
}