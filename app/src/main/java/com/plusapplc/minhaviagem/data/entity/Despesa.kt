package com.plusapplc.minhaviagem.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "despesas")
class Despesa(

    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val viagemId: Long,
    val nome:String,
    val valor:Double,
)
