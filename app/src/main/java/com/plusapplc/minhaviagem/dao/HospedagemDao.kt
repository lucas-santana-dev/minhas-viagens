package com.plusapplc.minhaviagem.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.plusapplc.minhaviagem.data.entity.Hospedagem


@Dao
interface HospedagemDao {

    @Insert
    suspend  fun inserirHospedagem(hospedagem: Hospedagem)

    @Delete
    suspend  fun deletarHospedagem(hospedagem: Hospedagem)

    @Update
    suspend   fun atualizarHospedagem(hospedagem: Hospedagem)

    @Query ("Select * From hospedagens Where viagemId = :idViagem")
    suspend fun hospedagensByViagem(idViagem : Long): List<Hospedagem>

}