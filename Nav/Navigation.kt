package com.example.resistencias.Nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screenA") {
        composable("screenA") { ScreenA(navController) }
        composable("screenB") { ScreenB(navController) }
        composable(
            "screenC/{valor}/{tolerancia}",
            arguments = listOf(
                navArgument("valor") { type = NavType.StringType },
                navArgument("tolerancia") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val valor = backStackEntry.arguments?.getString("valor") ?: ""
            val tolerancia = backStackEntry.arguments?.getString("tolerancia") ?: ""
            ScreenC(navController, valor, tolerancia)
        }
    }
}
