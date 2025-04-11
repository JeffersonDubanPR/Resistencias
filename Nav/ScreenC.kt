package com.example.resistencias.Nav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import androidx.compose.material3.*

import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenC(navController: NavController, valor: String, tolerancia: String) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Resultado") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text("Valor de la resistencia: $valor Ω", style = MaterialTheme.typography.headlineSmall)
            Text("Tolerancia: $tolerancia", style = MaterialTheme.typography.bodyLarge)

            Button(onClick = { navController.navigate("screenA") }) {
                Text("Volver al inicio")
            }
        }
    }
}



