package com.mr.pockemons.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mr.pockemons.data.local.AppDatabase
import com.mr.pockemons.data.local.PockemonEntity
import com.mr.pockemons.data.mappers.toFetchId
import com.mr.pockemons.data.mappers.toPockemonEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PockemonRemoteMediator(
    private val pockemonDb: AppDatabase,
    private val pockemonApi: ApiInterface

): RemoteMediator<Int, PockemonEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PockemonEntity>
    ): MediatorResult {
        return try {
            val  loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1

                    }
                }

            }
            val pockemons = pockemonApi.getPockemonsPage(
                offset = (loadKey-1)*20
            ).results
            Log.i("LOAD KEY ----", loadKey.toString())
            pockemonDb.withTransaction {
                if(loadType == LoadType.REFRESH){
                    pockemonDb.pockemonDao().clearAll()
                }
                val pockemonEntities = pockemons.map {
                    var fetchId = it.toPockemonEntity().id
                    try {
                        pockemonApi.getPockemonById(fetchId).toPockemonEntity()
                    } catch (e: Exception) {
                        PockemonEntity.failedPockemon(it.toPockemonEntity().id, it.name)
                    }
                    }
                pockemonDb.pockemonDao().upsertAll(pockemonEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = pockemons.isEmpty()
            )

        }catch( e: IOException){
            MediatorResult.Error(e)

        }catch (e: HttpException){
            MediatorResult.Error(e)
        }
    }

}