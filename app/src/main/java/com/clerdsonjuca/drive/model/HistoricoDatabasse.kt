package com.clerdsonjuca.drive.model

import androidx.room.Database
import androidx.room.RoomDatabase
import dagger.Provides


@Database(entities = [Historico::class],version = 1)
abstract class HistoricoDatabase:RoomDatabase() {
    abstract fun HistoricoDao():HistoricoDao
}