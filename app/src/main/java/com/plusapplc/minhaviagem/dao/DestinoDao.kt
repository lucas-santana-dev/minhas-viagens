package com.plusapplc.minhaviagem.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.plusapplc.minhaviagem.data.entity.Destino
import com.plusapplc.minhaviagem.data.entity.Hospedagem


@Dao
interface DestinoDao {

    @Insert
   suspend fun inserirDestino(destino: Destino)

    @Update
    suspend   fun updateDestino(destino: Destino)

    @Delete
    suspend  fun deletarDestino(destino: Destino)

    @Query ("Select * From destinos Where viagemId = :idViagem")
    suspend fun destinosByViagem(idViagem : Long): List<Destino>


}