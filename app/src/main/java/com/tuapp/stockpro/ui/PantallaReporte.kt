package com.tuapp.stockpro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
private val NaranjaAdvertencia = Color(0xFFF57F17)
private val VerdeOk = Color(0xFF2E7D32)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaReporte(
    navController: NavController,
    stockViewModel: StockViewModel
) {
    val totalInventario = stockViewModel.calcularValorTotalInventario()
    val productosEnCero = stockViewModel.productos.count { it.stockActual == 0 }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Reporte Financiero",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AzulMarino
                )
            )
        },
        containerColor = FondoPantalla
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Card principal con fondo azul marino
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AzulMarino)
                        .padding(28.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountBalance,
                            contentDescription = "Capital",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )

                        Text(
                            text = "Capital Invertido Total",
                            fontSize = 14.sp,
                            color = Color.White
                        )

                        Text(
                            text = "\$${String.format(Locale.US, "%,.2f", totalInventario)}",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            // Card secundaria de productos sin stock
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = if (productosEnCero > 0) Icons.Default.Warning else Icons.Default.CheckCircle,
                        contentDescription = if (productosEnCero > 0) "Advertencia" else "OK",
                        tint = if (productosEnCero > 0) NaranjaAdvertencia else VerdeOk,
                        modifier = Modifier.size(28.dp)
                    )

                    Text(
                        text = "Productos sin stock: $productosEnCero",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (productosEnCero > 0) NaranjaAdvertencia else VerdeOk
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulMarino,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Volver al Catálogo",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
