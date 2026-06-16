package com.tuapp.stockpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tuapp.stockpro.viewmodel.StockViewModel
import com.tuapp.stockpro.ui.PantallaLogin
import com.tuapp.stockpro.ui.PantallaCatalogo
import com.tuapp.stockpro.ui.PantallaEdicion
import com.tuapp.stockpro.ui.PantallaReporte

@Composable
fun NavGraph(
    navController: NavHostController,
    stockViewModel: StockViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "pantalla_login"
    ) {
        composable("pantalla_login") {
            PantallaLogin(navController)
        }

        composable(
            route = "pantalla_catalogo/{nombreOperario}",
            arguments = listOf(
                navArgument("nombreOperario") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val nombreOperario = backStackEntry.arguments?.getString("nombreOperario") ?: ""
            PantallaCatalogo(navController, nombreOperario, stockViewModel)
        }

        composable(
            route = "pantalla_edicion/{productoId}",
            arguments = listOf(
                navArgument("productoId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val productoId = backStackEntry.arguments?.getInt("productoId") ?: 0
            PantallaEdicion(navController, productoId, stockViewModel)
        }

        composable("pantalla_reporte") {
            PantallaReporte(navController, stockViewModel)
        }
    }
}

