package com.mr.pockemons.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.mr.pockemons.PockemonApp
import com.mr.pockemons.data.local.AppDatabase
import com.mr.pockemons.data.local.PockemonDao
import com.mr.pockemons.data.local.PockemonEntity
import com.mr.pockemons.data.remote.ApiInterface
import com.mr.pockemons.data.remote.PockemonRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): PockemonApp {
        return app as PockemonApp
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "our_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideBeerApi(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

    }


    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeerPager(database: AppDatabase, api: ApiInterface): Pager<Int, PockemonEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 15, initialLoadSize = 60),
            remoteMediator = PockemonRemoteMediator(
                pockemonDb = database,
                pockemonApi = api
            ),
            pagingSourceFactory = {
                database.pockemonDao().pagingSource()
            }
        )
    }

}