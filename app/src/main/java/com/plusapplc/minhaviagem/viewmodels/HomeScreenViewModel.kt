package com.plusapplc.minhaviagem.viewmodels

import androidx.lifecycle.ViewModel
import com.plusapplc.minhaviagem.data.entity.Viagem
import com.plusapplc.minhaviagem.database.AppDatabase

class HomeScreenViewModel(private val appDatabase: AppDatabase) : ViewModel() {
    suspend fun getListViagens(): List<Viagem> {
        val listaDeViagens = appDatabase.viagemDao().getAll()
        return listaDeViagens
    }

}