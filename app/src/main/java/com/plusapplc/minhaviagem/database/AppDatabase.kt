package com.plusapplc.minhaviagem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.plusapplc.minhaviagem.dao.AtividadeDao
import com.plusapplc.minhaviagem.dao.DespesasDao
import com.plusapplc.minhaviagem.dao.DestinoDao
import com.plusapplc.minhaviagem.dao.HospedagemDao
import com.plusapplc.minhaviagem.dao.ViagemDao
import com.plusapplc.minhaviagem.data.converter.Converters
import com.plusapplc.minhaviagem.data.entity.Atividade
import com.plusapplc.minhaviagem.data.entity.Despesa
import com.plusapplc.minhaviagem.data.entity.Destino
import com.plusapplc.minhaviagem.data.entity.Hospedagem
import com.plusapplc.minhaviagem.data.entity.Viagem

@Database(
    entities = [
        Viagem::class,
        Despesa::class,
        Destino::class,
        Hospedagem::class,
        Atividade::class
    ],
    version = 1,
    exportSchema = true

)
@TypeConverters(Converters::class)
abstract class AppDatabase() : RoomDatabase() {

    abstract fun viagemDao(): ViagemDao
    abstract fun despesasDao(): DespesasDao
    abstract fun hospedagemDao(): HospedagemDao
    abstract fun destinoDao(): DestinoDao
    abstract fun atividadeDao(): AtividadeDao


    companion object{
        @Volatile
        private var db: AppDatabase? = null
        fun instancia(context: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "appdatabase.db"
            ).build().also { db = it }
        }
    }

}