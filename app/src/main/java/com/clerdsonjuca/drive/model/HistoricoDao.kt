package com.clerdsonjuca.drive.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

@Dao
interface HistoricoDao {
    @Query("SELECT * FROM historicos")
    fun getAllHistorico():Flow<List<Historico>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historico: List<Historico>)

    @Query("DELETE FROM historicos")
    suspend fun delete()
}