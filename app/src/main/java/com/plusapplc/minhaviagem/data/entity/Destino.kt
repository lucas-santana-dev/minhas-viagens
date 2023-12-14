package com.plusapplc.minhaviagem.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.IDN

@Entity(tableName = "destinos")
data class Destino(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val viagemId: Long,
    val hospedagemId: Long,
    val nome:String,
)
