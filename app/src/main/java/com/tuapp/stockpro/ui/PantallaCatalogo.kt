package com.tuapp.stockpro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tuapp.stockpro.viewmodel.StockViewModel
import java.util.Locale

private val AzulMarino = Color(0xFF1A237E)
private val AzulSecundario = Color(0xFF283593)
private val FondoPantalla = Color(0xFFF5F5F5)
private val VerdeStock = Color(0xFF2E7D32)

@OptIn(ExperimentalMaterial3Api::class)
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
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "StockPro - Inventario",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AzulMarino
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("pantalla_reporte") },
                containerColor = AzulMarino,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Assessment,
                    contentDescription = "Ver Reporte"
                )
            }
        },
        containerColor = FondoPantalla
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Operario",
                    tint = AzulMarino,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Operario: $nombreOperario",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulMarino
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (!mostrarCriticos) {
                    Button(
                        onClick = { mostrarCriticos = false },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AzulMarino,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Ver Todo", fontWeight = FontWeight.SemiBold)
                    }
                } else {
                    OutlinedButton(
                        onClick = { mostrarCriticos = false },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, AzulMarino),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = AzulMarino
                        )
                    ) {
                        Text("Ver Todo", fontWeight = FontWeight.SemiBold)
                    }
                }

                if (mostrarCriticos) {
                    Button(
                        onClick = { mostrarCriticos = true },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AzulMarino,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Stock Crítico", fontWeight = FontWeight.SemiBold)
                    }
                } else {
                    OutlinedButton(
                        onClick = { mostrarCriticos = true },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, AzulMarino),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = AzulMarino
                        )
                    ) {
                        Text("Stock Crítico", fontWeight = FontWeight.SemiBold)
                    }
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
                            },
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = producto.nombre,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.LocalOffer,
                                    contentDescription = "Precio",
                                    tint = AzulMarino,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "\$${String.format(Locale.US, "%.2f", producto.precio)}",
                                    fontSize = 14.sp,
                                    color = Color.DarkGray
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Inventory,
                                    contentDescription = "Stock",
                                    tint = if (producto.stockActual < 5) Color.Red else VerdeStock,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Stock: ${producto.stockActual}",
                                    fontSize = 14.sp,
                                    color = if (producto.stockActual < 5) Color.Red else VerdeStock,
                                    fontWeight = if (producto.stockActual < 5) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
