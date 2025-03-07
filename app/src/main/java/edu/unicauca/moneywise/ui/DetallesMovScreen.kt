package edu.unicauca.moneywise.ui

import MoneyWiseViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetallesMovScreen(
    movimiento: Movimiento?,
    onBackClick: () -> Unit
) {
    // Definiendo colores directamente en el archivo
    val greenPrimary = Color(0xFF1B5E20)
    val greenSecondary = Color(0xFF81C784)
    val greenAccent = Color(0xFF00E676)
    val white = Color(0xFFFFFFFF)
    val backgroundGradient = listOf(greenPrimary, greenSecondary)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = backgroundGradient,
                    startY = 200f
                )
            )
    ) {
        Scaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Detalles del Movimiento",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = white
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .background(
                            color = greenAccent,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(vertical = 12.dp, horizontal = 24.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = white)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Fecha: ${movimiento!!.fecha}",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Categoría: ${movimiento.categoria}",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Descripción: ${movimiento.descripcion}",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Monto: ${movimiento.monto}",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Tipo de Movimiento: ${movimiento.tipoMovimiento}",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }

                Button(
                    onClick = onBackClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = greenAccent,
                        contentColor = white
                    ),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .height(55.dp)
                        .shadow(12.dp, shape = RoundedCornerShape(30.dp))
                ) {
                    Text("Regresar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
