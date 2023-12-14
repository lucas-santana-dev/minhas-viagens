package com.plusapplc.minhaviagem.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.plusapplc.minhaviagem.data.entity.Atividade


@Dao
interface AtividadeDao {


    @Insert
    suspend fun inserirAtivdade(atividade: Atividade)

    @Update
    suspend fun atualizarAtividade(atividade: Atividade)

    @Delete
    suspend fun deletarAtividade(atividade: Atividade)

    @Query("Select * From atividades where viagemId = :idViagem")
    suspend fun atividadesByViagem(idViagem: Long): List<Atividade>

}