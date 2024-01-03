package com.plusapplc.minhaviagem.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "hospedagens")
data class Hospedagem (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val viagemId:Long,
    val nome:String,
    val diarias: Int,
    val valorDiaria: Double

)
