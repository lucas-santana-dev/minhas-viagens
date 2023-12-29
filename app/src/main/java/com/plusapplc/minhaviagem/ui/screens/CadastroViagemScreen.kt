package com.plusapplc.minhaviagem.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.plusapplc.minhaviagem.data.entity.Viagem
import com.plusapplc.minhaviagem.viewmodels.CadastroViagemViewModel
import com.plusapplc.minhaviagem.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun CadastroViagemScreen(navController: NavController) {
    val viewModel: CadastroViagemViewModel = koinInject<CadastroViagemViewModel>()
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val windowSizeClass = calculateWindowSizeClass(context as Activity)
    val drawerWidth = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Expanded -> 350.dp
        WindowWidthSizeClass.Medium, WindowWidthSizeClass.Compact -> 300.dp
        else -> 250.dp
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)



    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(drawerWidth)) {

            }
        }) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            text = "Cadastro de Viagem",
                            fontWeight = FontWeight.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Perfil"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    FormCadastroViagem(
                        cadastroViagemViewModel = viewModel,
                        navController = navController
                    )
                }

            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun FormCadastroViagem(
    navController: NavController,
    cadastroViagemViewModel: CadastroViagemViewModel,
    viagemId: Long? = null) {

    var nomeViagem by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    var descricaoViagem by remember { mutableStateOf("") }
    var dataPartida by remember { mutableStateOf("") }
    var dataRetorno by remember { mutableStateOf("") }
    var orcamentoViagem by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        nomeViagem = cadastroViagemViewModel.getNomeViagemById(viagemId = viagemId) ?: ""
        descricaoViagem = cadastroViagemViewModel.getDescricaoViagemById(viagemId = viagemId) ?: ""
        dataPartida = cadastroViagemViewModel.getPartidaById(viagemId) ?: ""
        dataRetorno = cadastroViagemViewModel.getRetornoById(viagemId) ?: ""
        orcamentoViagem = cadastroViagemViewModel.getOrcamentoById(viagemId) ?: ""
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        maxLines = 1,
        label = { Text(text = "Qual nome para sua viagem?") },
        value = nomeViagem,
        onValueChange =
        { novoNome ->
            nomeViagem = novoNome
        }
    )

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        maxLines = 3,
        label = { Text(text = "A sua melhor descrição para ela") },
        value = descricaoViagem,
        onValueChange = { novaDescricao ->
            descricaoViagem = novaDescricao
        }
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        maxLines = 1,
        label = { Text(text = "Quando você vai?") },
        value = dataPartida,
        onValueChange = { novaDataPartida ->
            dataPartida = novaDataPartida
        }
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        maxLines = 1,
        label = { Text(text = "Data para retorno") },
        value = dataRetorno,
        onValueChange = { novaDataRetorno ->
            dataRetorno = novaDataRetorno
        }
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        maxLines = 1,
        label = { Text(text = "Qual seu orçamento?") },
        value = orcamentoViagem,
        onValueChange = { novoOrcamento ->
            orcamentoViagem = novoOrcamento
        }
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
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
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Voltar")
        }
    }

}
