package com.plusapplc.minhaviagem.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "atividades")
data class Atividade (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val viagemId: Long,
    val destinoId: Long,
    val nome: String,
    val  descricao: String
)