package com.plusapplc.minhaviagem.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plusapplc.minhaviagem.R
import com.plusapplc.minhaviagem.utils.NumberFormatter
import com.plusapplc.minhaviagem.utils.Numbers
import com.plusapplc.minhaviagem.viewmodels.CadastroViagemViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@Composable
fun FormCadastroViagem(
    navController: NavController,
    cadastroViagemViewModel: CadastroViagemViewModel,
    viagemId: Long? = null
) {

    var nomeViagem by remember {
        mutableStateOf("")
    }
    var descricaoViagem by remember { mutableStateOf("") }
    var dataPartida by remember { mutableStateOf("") }
    var dataRetorno by remember { mutableStateOf("") }
    var orcamentoViagem by remember { mutableStateOf(0.0) }
    val decimalFormatter = NumberFormatter.decimal(true, false)

    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        nomeViagem = cadastroViagemViewModel.getNomeViagemById(viagemId = viagemId) ?: ""
        descricaoViagem = cadastroViagemViewModel.getDescricaoViagemById(viagemId = viagemId) ?: ""
        dataPartida = cadastroViagemViewModel.getPartidaById(viagemId) ?: ""
        dataRetorno = cadastroViagemViewModel.getRetornoById(viagemId) ?: ""
        orcamentoViagem =
            ((cadastroViagemViewModel.getOrcamentoById(viagemId) ?: 0.0) as Double)
    }
    var stateOrcamento by remember {
        mutableStateOf(
            TextFieldValue(
                decimalFormatter.format(orcamentoViagem)
            )
        )
    }
    LazyColumn {
        item {
            Column {
                Text(
                    text = "Adicione uma imagem para representar sua viagem",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(331.dp)
                        .clickable { }
                ) {
                    Image(
                        painterResource(id = R.drawable.img),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
        item {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                maxLines = 1,
                label = { Text(text = "Nome da viagem:") },
                value = nomeViagem.take(50),
                onValueChange =
                { novoNome ->
                    nomeViagem = novoNome
                },

                )
        }
        item {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                maxLines = 3,
                label = { Text(text = "Descrição:") },
                value = descricaoViagem,
                onValueChange = { novaDescricao ->
                    descricaoViagem = novaDescricao
                }
            )
        }
        item {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                maxLines = 1,
                label = { Text(text = "Data de Partida:") },
                value = dataPartida,
                onValueChange = { novaDataPartida ->
                    dataPartida = novaDataPartida
                }
            )
        }
        item {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                maxLines = 1,
                label = { Text(text = "Data de Retorno:") },
                value = dataRetorno,
                onValueChange = { novaDataRetorno ->
                    dataRetorno = novaDataRetorno
                }
            )
        }
        fun formatForVisual(value: Double): String {
            return String.format("%,.2f", value)
        }
        item {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                maxLines = 1,
                label = { Text(text = "Orçamento da viagem:") },
                value = stateOrcamento,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { novoOrcamento: TextFieldValue ->
                    orcamentoViagem =
                        Numbers.strToDouble(Numbers.formatCurrency(novoOrcamento.text), 0.0)
                    stateOrcamento = TextFieldValue(
                        decimalFormatter.format(orcamentoViagem),
                        selection = TextRange(decimalFormatter.format(orcamentoViagem).length)
                    )
                },

                )
        }

        item {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.End

            ) {

                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "Voltar")
                }
                Spacer(
                    modifier = Modifier.padding(
                        10.dp
                    )
                )
                Button(onClick = {
                    scope.launch {
                        cadastroViagemViewModel.salvarViagem(
                            nome = nomeViagem,
                            descricao = descricaoViagem,
                            dataPartidaStr = dataPartida,
                            dataRetornoStr = dataRetorno,
                            orcamento = orcamentoViagem.toDouble()
                        )
                    }

                }) {
                    Text("Salvar")
                }
            }
        }
    }


}
