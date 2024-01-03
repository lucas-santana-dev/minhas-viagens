package com.plusapplc.minhaviagem.viewmodels

import androidx.lifecycle.ViewModel
import com.plusapplc.minhaviagem.dao.AtividadeDao
import com.plusapplc.minhaviagem.dao.DespesasDao
import com.plusapplc.minhaviagem.dao.DestinoDao
import com.plusapplc.minhaviagem.dao.HospedagemDao
import com.plusapplc.minhaviagem.dao.ViagemDao
import com.plusapplc.minhaviagem.data.entity.Destino
import com.plusapplc.minhaviagem.data.entity.Hospedagem
import com.plusapplc.minhaviagem.data.entity.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class CadastroViagemViewModel(
    private val viagemDao: ViagemDao,
    private val atividadeDao: AtividadeDao,
    private val hospedagemDao: HospedagemDao,
    private val despesasDao: DespesasDao,
    private val destinoDao: DestinoDao,
) : ViewModel() {

    suspend fun getNomeViagemById(viagemId: Long? = null): String? {
        val nome = viagemDao.findNomeById(viagemId)
        return if (nome.isNullOrEmpty()) {
            null
        } else {
            nome
        }
    }

    suspend fun getDescricaoViagemById(viagemId: Long? = null): String? {
        val descricao = viagemDao.findDescricaoById(viagemId)
        return if (descricao.isNullOrEmpty()) {
            null
        } else {
            descricao
        }
    }

    suspend fun getPartidaById(viagemId: Long? = null): String? {
        val dataPartida = viagemDao.findPartidaById(viagemId)

        // Se dataPartida for nula, retorna null; caso contrário, formate a data
        return dataPartida?.let {
            // Formate a data para uma string usando SimpleDateFormat
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            sdf.format(it)
        }
    }

    suspend fun getRetornoById(viagemId: Long? = null): String? {
        val dataRetorno = viagemDao.findRetornoById(viagemId)

        // Se dataPartida for nula, retorna null; caso contrário, formate a data
        return dataRetorno?.let {
            // Formate a data para uma string usando SimpleDateFormat
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            sdf.format(it)
        }
    }

    suspend fun getOrcamentoById(viagemId: Long? = null): String? {
        val orcamento = viagemDao.findOrcamentoById(viagemId)
        return orcamento?.let {
            orcamento.toString()
        }
    }
    suspend fun salvarViagem(
        nome: String,
        descricao: String,
        dataPartida: String,
        dataRetorno: String,
        orcamento: Double,
        destino: String,
        hospedagem: String,
        valorDiaria: Double,
        diarias : Int
    ) {
        withContext(Dispatchers.IO) {
            val formatoData = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dataPartida = formatoData.parse(dataPartida)
            val dataRetorno = formatoData.parse(dataRetorno)
            val novaViagem = Viagem(
                id = 0,
                nome = nome,
                descricao = descricao,
                dataPartida = dataPartida,
                dataRetorno = dataRetorno,
                orcamento = orcamento
            )
            val viagemId = viagemDao.inserirViagem(novaViagem)
            val novaHospedagem = Hospedagem(
                id = 0,
                nome = hospedagem,
                diarias =diarias ,
                valorDiaria = valorDiaria,
                viagemId = viagemId
            )
            val hospedagemId = hospedagemDao.inserirHospedagem(novaHospedagem)
            val novoDestino = Destino(
                id = 0,
                nome = destino,
                hospedagemId = hospedagemId,
                viagemId = viagemId,
            )
            destinoDao.inserirDestino(novoDestino)
        }
    }
}