package com.plusapplc.minhaviagem.viewmodels

import androidx.lifecycle.ViewModel
import com.plusapplc.minhaviagem.dao.DestinoDao
import com.plusapplc.minhaviagem.dao.ViagemDao
import com.plusapplc.minhaviagem.data.entity.Destino
import com.plusapplc.minhaviagem.data.entity.Viagem
import com.plusapplc.minhaviagem.database.AppDatabase

class HomeScreenViewModel(private val viagemDao: ViagemDao, private val destinoDao: DestinoDao) : ViewModel() {

    suspend fun getListViagens(): List<Viagem> {
        val listaDeViagens = viagemDao.getAll()
        return listaDeViagens
    }
     fun getDestinoNomebyIdViagem(viagemId: Long): String{
        val destino = destinoDao.destinosByViagem(viagemId)
        return destino.nome
    }

}