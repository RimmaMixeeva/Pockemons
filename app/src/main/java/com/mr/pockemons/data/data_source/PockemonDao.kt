package com.mr.pockemons.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.mr.pockemons.domain.model.main.PockemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PockemonDao {
    @Query("SELECT * FROM PockemonTable")
    fun getAllPockemons(): LiveData<List<PockemonEntity>>

    @Query("SELECT * FROM PockemonTable WHERE id = :id")
    fun getPockemonById(id: Int): LiveData<List<PockemonEntity>>

    @Upsert
    suspend fun upsert(pockemons: PockemonEntity)



}