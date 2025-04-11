package com.example.resistencias.Nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenB(navController: NavController) {
    val coloresValor = listOf("Negro", "Marrón", "Rojo", "Naranja", "Amarillo", "Verde", "Azul", "Violeta", "Gris", "Blanco")
    val coloresMultiplicador = listOf("Negro", "Marrón", "Rojo", "Naranja", "Amarillo")
    val coloresTolerancia = listOf("Ninguno", "Dorado", "Plateado")

    val mapaValor = mapOf(
        "Negro" to 0, "Marrón" to 1, "Rojo" to 2, "Naranja" to 3,
        "Amarillo" to 4, "Verde" to 5, "Azul" to 6, "Violeta" to 7,
        "Gris" to 8, "Blanco" to 9
    )

    val mapaMultiplicador = mapOf(
        "Negro" to 1, "Marrón" to 10, "Rojo" to 100, "Naranja" to 1000, "Amarillo" to 10000
    )

    val mapaTolerancia = mapOf(
        "Ninguno" to "±20", "Dorado" to "±5", "Plateado" to "±10"
    )

    var banda1 by remember { mutableStateOf(coloresValor.first()) }
    var banda2 by remember { mutableStateOf(coloresValor.first()) }
    var banda3 by remember { mutableStateOf(coloresMultiplicador.first()) }
    var banda4 by remember { mutableStateOf(coloresTolerancia.first()) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Selecciona las Bandas") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DropdownSelector("Banda 1", coloresValor, banda1) { banda1 = it }
            DropdownSelector("Banda 2", coloresValor, banda2) { banda2 = it }
            DropdownSelector("Multiplicador (Banda 3)", coloresMultiplicador, banda3) { banda3 = it }
            DropdownSelector("Tolerancia", coloresTolerancia, banda4) { banda4 = it }

            Button(onClick = {
                val valor1 = mapaValor[banda1]
                val valor2 = mapaValor[banda2]
                val multiplicador = mapaMultiplicador[banda3]
                val tolerancia = mapaTolerancia[banda4]

                if (valor1 != null && valor2 != null && multiplicador != null && tolerancia != null) {
                    val total = (valor1 * 10 + valor2) * multiplicador
                    navController.navigate("screenC/$total/$tolerancia")
                } else {
                    Log.e("ResistenciaApp", "Error: Selección inválida")
                }
            }) {
                Text("Calcular")
            }
        }
    }
}

@Composable
fun DropdownSelector(label: String, opciones: List<String>, seleccionado: String, onChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Text(label)
        OutlinedButton(onClick = { expanded = true }) {
            Text(seleccionado)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        onChange(opcion)
                        expanded = false
                    }
                )
            }
        }
    }
}
