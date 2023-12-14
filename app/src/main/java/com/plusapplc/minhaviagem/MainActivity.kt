package com.plusapplc.minhaviagem

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plusapplc.minhaviagem.data.entity.Destino
import com.plusapplc.minhaviagem.data.entity.Status
import com.plusapplc.minhaviagem.data.entity.Viagem
import com.plusapplc.minhaviagem.database.AppDatabase
import com.plusapplc.minhaviagem.ui.theme.MinhaViagemTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinhaViagemTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var viagemNome = ""
                    var viagemPartida = Date()
                    var viagemDataRetorno = Date()
                    var viagemOrcamento = 0.0
                    Column {
                        Text(text = "Cadastro de Viagems")
                        OutlinedTextField(
                            value = viagemNome,
                            onValueChange = { newvalue ->
                            viagemNome = newvalue},

                        )
                    }

                }
            }
        }
    }
}

