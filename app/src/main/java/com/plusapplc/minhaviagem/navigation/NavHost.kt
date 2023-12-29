package com.plusapplc.minhaviagem.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plusapplc.minhaviagem.ui.screens.CadastroViagemScreen
import com.plusapplc.minhaviagem.ui.screens.HomeScreen

@Composable
fun NavHost() {
    val navController = rememberNavController()
    val startDestination = Destination.HomeScreen.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destination.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(Destination.CadastroViagemScreen.route) {
         CadastroViagemScreen(navController = navController)
        }
    }
}