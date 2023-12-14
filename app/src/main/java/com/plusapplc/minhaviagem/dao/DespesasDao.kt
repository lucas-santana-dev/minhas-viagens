package com.plusapplc.minhaviagem.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.plusapplc.minhaviagem.data.entity.Despesa
import com.plusapplc.minhaviagem.data.entity.Hospedagem


@Dao
interface DespesasDao {

    @Insert
    suspend   fun inserirDespesa(despesa: Despesa)

    @Delete
    suspend  fun deletarDespesa(despesa: Despesa)

    @Update
    suspend  fun atualizarDespesa(despesa: Despesa)

    @Query("SELECT SUM(valor) FROM despesas")
    suspend  fun calcularValorTotalDespesas(): Double

    @Query ("Select * From despesas Where viagemId = :idViagem")
    suspend fun despesasByViagem(idViagem : Long): List<Despesa>

}