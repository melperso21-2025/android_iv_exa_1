package com.tuapp.stockpro.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PantallaLogin(navController: NavController) {
    var nombre by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bienvenido a StockPro",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Ingrese su nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Button(
            onClick = {
                navController.navigate("pantalla_catalogo/$nombre")
            },
            enabled = nombre.length >= 3,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text("Ingresar al Sistema")
        }
    }
}

