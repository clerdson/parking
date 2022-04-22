package com.clerdsonjuca.drive.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historicos")
data class Historico (
    @PrimaryKey val time:String,
val  paid:Boolean,
val left:Boolean,
val plate:String,
val reservation: String
        )