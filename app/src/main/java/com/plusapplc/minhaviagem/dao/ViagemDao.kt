package com.plusapplc.minhaviagem.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.plusapplc.minhaviagem.data.entity.Viagem

@Dao
interface ViagemDao {

    @Query("select * from viagens")
    suspend  fun getAll(): List<Viagem>

    @Insert
    suspend  fun inserirViagem(viagem: Viagem)

    @Update
    suspend  fun atualizarViagem(viagem: Viagem)

    @Delete
    suspend  fun deletarViagem(viagem: Viagem)



}