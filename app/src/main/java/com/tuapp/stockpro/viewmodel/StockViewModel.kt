package com.tuapp.stockpro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.tuapp.stockpro.model.Producto

class StockViewModel : ViewModel() {

    val productos = mutableStateListOf(
        Producto(
            id = 1,
            nombre = "Arroz",
            descripcion = "Arroz blanco de grano largo",
            precio = 2.50,
            stockActual = 150
        ),
        Producto(
            id = 2,
            nombre = "Azúcar",
            descripcion = "Azúcar granulada premium",
            precio = 1.80,
            stockActual = 3
        ),
        Producto(
            id = 3,
            nombre = "Aceite",
            descripcion = "Aceite vegetal puro",
            precio = 5.00,
            stockActual = 45
        ),
        Producto(
            id = 4,
            nombre = "Leche",
            descripcion = "Leche descremada en polvo",
            precio = 3.50,
            stockActual = 2
        ),
        Producto(
            id = 5,
            nombre = "Harina",
            descripcion = "Harina de trigo para repostería",
            precio = 2.00,
            stockActual = 200
        ),
        Producto(
            id = 6,
            nombre = "Atún",
            descripcion = "Atún en lata al natural",
            precio = 1.50,
            stockActual = 75
        ),
        Producto(
            id = 7,
            nombre = "Sal",
            descripcion = "Sal yodada de mesa 1kg",
            precio = 0.75,
            stockActual = 3
        ),
        Producto(
            id = 8,
            nombre = "Fideo",
            descripcion = "Fideo tallarín de trigo 500g",
            precio = 1.20,
            stockActual = 80
        ),
        Producto(
            id = 9,
            nombre = "Mantequilla",
            descripcion = "Mantequilla sin sal 200g",
            precio = 2.80,
            stockActual = 4
        ),
        Producto(
            id = 10,
            nombre = "Café",
            descripcion = "Café molido tradicional 250g",
            precio = 4.50,
            stockActual = 25
        )
    )

    fun obtenerProducto(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val indice = productos.indexOfFirst { it.id == id }
        if (indice != -1) {
            val producto = productos[indice]
            productos[indice] = producto.copy(stockActual = nuevaCantidad)
        }
    }

    fun calcularValorTotalInventario(): Double {
        return productos.sumOf { it.precio * it.stockActual }
    }

    fun obtenerProductosEnRiesgo(): List<Producto> {
        return productos.filter { it.stockActual < 5 }
    }
}
