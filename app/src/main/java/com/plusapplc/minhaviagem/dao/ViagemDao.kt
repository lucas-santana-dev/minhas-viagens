package com.plusapplc.minhaviagem.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.plusapplc.minhaviagem.data.entity.Status
import com.plusapplc.minhaviagem.data.entity.Viagem
import java.util.Date

@Dao
interface ViagemDao {

    @Query("select * from viagens")
    suspend fun getAll(): List<Viagem>

    @Insert
    suspend fun inserirViagem(viagem: Viagem)

    @Update
    suspend fun atualizarViagem(viagem: Viagem)

    @Delete
    suspend fun deletarViagem(viagem: Viagem)

    @Query("SELECT nome FROM viagens WHERE id = :id")
    suspend fun findNomeById(id: Long?): String?

    @Query("SELECT descricao FROM viagens WHERE id = :id")
    suspend fun findDescricaoById(id: Long?): String?

    @Query("SELECT dataPartida FROM viagens WHERE id = :id")
    suspend fun findPartidaById(id: Long?): Date?

    @Query("SELECT dataRetorno FROM viagens WHERE id = :id")
    suspend fun findRetornoById(id: Long?): Date?

    @Query("SELECT orcamento FROM viagens WHERE id = :id")
    suspend fun findOrcamentoById(id: Long?): Double?

    @Query("SELECT status FROM viagens WHERE id = :id")
    suspend fun findStatusById(id: Long?): Status?

}