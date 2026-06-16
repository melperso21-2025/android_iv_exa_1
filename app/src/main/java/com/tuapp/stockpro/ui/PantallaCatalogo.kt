package com.tuapp.stockpro.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tuapp.stockpro.viewmodel.StockViewModel

@Composable
fun PantallaCatalogo(
    navController: NavController,
    nombreOperario: String,
    stockViewModel: StockViewModel
) {
    var mostrarCriticos by remember { mutableStateOf(false) }

    val listaProductos = if (mostrarCriticos) {
        stockViewModel.obtenerProductosEnRiesgo()
    } else {
        stockViewModel.productos
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("pantalla_reporte")
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Ir a Reporte")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Operario: $nombreOperario",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { mostrarCriticos = false },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Ver Todo")
                }

                Button(
                    onClick = { mostrarCriticos = true },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Stock Crítico")
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listaProductos) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("pantalla_edicion/${producto.id}")
                            }
                            .padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = producto.nombre,
                                fontSize = 16.sp
                            )

                            Text(
                                text = "Precio: $${String.format("%.2f", producto.precio)}",
                                fontSize = 14.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                text = "Stock: ${producto.stockActual}",
                                fontSize = 14.sp,
                                color = if (producto.stockActual < 5) Color.Red else Color.Black,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

