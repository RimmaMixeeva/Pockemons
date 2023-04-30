package com.mr.pockemons.data.repository

import androidx.lifecycle.LiveData
import com.mr.pockemons.data.local.PockemonDao
import com.mr.pockemons.data.local.PockemonEntity


class DataRepository(private val pockemonDao: PockemonDao) {

    val readAllData: LiveData<List<PockemonEntity>> = pockemonDao.getAllPockemons()

    fun readById(id: Int): LiveData<PockemonEntity> {
        return pockemonDao.getPockemonById(id)
    }
    suspend fun upsert(pockemon: PockemonEntity){
        pockemonDao.upsert(pockemon)
    }

}