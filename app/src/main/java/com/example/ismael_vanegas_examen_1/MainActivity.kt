package com.example.ismael_vanegas_examen_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.ismael_vanegas_examen_1.ui.theme.Ismael_vanegas_examen_1Theme
import com.tuapp.stockpro.navigation.NavGraph
import com.tuapp.stockpro.viewmodel.StockViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ismael_vanegas_examen_1Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val stockViewModel: StockViewModel = viewModel()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        NavGraph(navController, stockViewModel)
    }
}