package com.tuapp.stockpro.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tuapp.stockpro.viewmodel.StockViewModel

@Composable
fun PantallaEdicion(
    navController: NavController,
    productoId: Int,
    stockViewModel: StockViewModel
) {
    val producto = stockViewModel.obtenerProducto(productoId)

    if (producto == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Producto no encontrado")
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = producto.nombre,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = producto.descripcion,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Text(
                text = "Stock: ${producto.stockActual}",
                fontSize = 32.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Button(
                    onClick = {
                        stockViewModel.actualizarStock(productoId, producto.stockActual - 1)
                    },
                    enabled = producto.stockActual > 0,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("-")
                }

                Button(
                    onClick = {
                        stockViewModel.actualizarStock(productoId, producto.stockActual + 1)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("+")
                }
            }

            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Guardar y Volver")
            }
        }
    }
}

