package com.plusapplc.minhaviagem.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "viagens")
data class Viagem(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val nome: String,
    val dataPartida: Date,
    val dataRetorno: Date,
    val orcamento: Double,
    val status: Status = Status.EM_PLANEJAMENTO  // Valor padrão definido aqui
)

enum class Status {
    EM_PLANEJAMENTO,
    EM_CURSO,
    FINALIZADO
}