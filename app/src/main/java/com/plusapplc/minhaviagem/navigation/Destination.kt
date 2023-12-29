package com.plusapplc.minhaviagem.navigation

sealed class Destination(val route: String) {

    object HomeScreen : Destination("home_screen")
    object CadastroViagemScreen : Destination("cadastro_viagem")

}