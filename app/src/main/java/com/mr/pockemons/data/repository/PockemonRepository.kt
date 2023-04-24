package com.mr.pockemons.data.repository

import androidx.lifecycle.LiveData
import com.mr.pockemons.data.data_source.PockemonDao
import com.mr.pockemons.domain.model.main.PockemonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


class PockemonRepository(private val pockemonDao: PockemonDao) {

    val readAllData: LiveData<List<PockemonEntity>> = pockemonDao.getAllPockemons()

    fun readAllRegex(id: Int): LiveData<List<PockemonEntity>> {
        return pockemonDao.getPockemonById(id)
    }
    suspend fun upsert(pockemon: PockemonEntity){
        pockemonDao.upsert(pockemon)
    }

}