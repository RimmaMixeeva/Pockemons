package com.mr.pockemons.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PockemonDao {
    @Query("SELECT * FROM PockemonTable")
    fun getAllPockemons(): LiveData<List<PockemonEntity>>

    @Query("SELECT * FROM PockemonTable WHERE id = :id")
    fun getPockemonById(id: Int): LiveData<PockemonEntity>

    @Upsert
    suspend fun upsertAll(pockemons: List<PockemonEntity>)

    @Upsert
    suspend fun upsert(pockemon: PockemonEntity)

    @Query("DELETE FROM PockemonTable")
    suspend fun clearAll()

    @Query("SELECT * FROM PockemonTable")
    fun pagingSource(): PagingSource<Int, PockemonEntity>



}