

package edu.unicauca.moneywise.ui

import MoneyWiseViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.moneywise.R

data class Movimiento(
    val id: Long,
    val fecha: String,
    val categoria: String,
    val descripcion: String,
    val monto: Double,
    val tipoMovimiento: String
)


@Composable
fun MovimientosScreen(
    movimientos: List<Movimiento>,
    onEditarClicked: (Movimiento?) -> Unit,
    onAgregarClicked: () -> Unit,
    onDetallesClicked: (Movimiento?) -> Unit,
    onEliminarClicked: (Movimiento?) -> Unit
) {
    var movimientoSeleccionado by remember { mutableStateOf<Movimiento?>(null) }

    // Colores definidos
    val greenPrimary = Color(0xFF1B5E20)
    val greenSecondary = Color(0xFF81C784)
    val white = Color(0xFFFFFFFF)
    val backgroundColor = Color(0xFFE0E0E0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(greenSecondary)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Movimientos",
                color = white,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .background(greenPrimary)
                    .padding(16.dp)
                    .shadow(2.dp, shape = RoundedCornerShape(8.dp))
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE0E0E0))
                    .border(BorderStroke(1.dp, Color.Gray)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                EncabezadoCelda("Tipo", Modifier.weight(1f))
                EncabezadoCelda("Fecha", Modifier.weight(1f))
                EncabezadoCelda("Descripción", Modifier.weight(2f))
                EncabezadoCelda("Monto", Modifier.weight(1f))
            }

            Divider(color = Color.Gray, thickness = 1.dp)

            LazyColumn {
                items(movimientos) { movimiento ->
                    val esSeleccionado = movimiento == movimientoSeleccionado

                    MovimientoRow(
                        movimiento = movimiento,
                        esSeleccionado = esSeleccionado,
                        onClick = {
                            movimientoSeleccionado = movimiento
                        }
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { onAgregarClicked() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = greenPrimary,
                    contentColor = white
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Agregar")
            }
            Button(
                onClick = { onEditarClicked(movimientoSeleccionado) },
                enabled = movimientoSeleccionado != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = greenPrimary,
                    contentColor = white
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Editar")
            }
            Button(
                onClick = { onDetallesClicked(movimientoSeleccionado) },
                enabled = movimientoSeleccionado != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = greenPrimary,
                    contentColor = white
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Detalles")
            }
            Button(
                onClick = { onEliminarClicked(movimientoSeleccionado) },
                enabled = movimientoSeleccionado != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = greenPrimary,
                    contentColor = white
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Eliminar")
            }
        }
    }
}

@Composable
fun EncabezadoCelda(texto: String, modifier: Modifier = Modifier) {
    Text(
        text = texto,
        fontSize = 12.sp,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .padding(8.dp)
            .border(BorderStroke(1.dp, Color.Gray))
            .background(Color.LightGray)
            .padding(8.dp)
    )
}

@Composable
fun MovimientoRow(
    movimiento: Movimiento,
    esSeleccionado: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (esSeleccionado) Color(0xFF8BC34A) else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp)
            .background(backgroundColor)
            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(4.dp))
            .padding(2.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CeldaTexto(movimiento.tipoMovimiento, Modifier.weight(1f))
        CeldaTexto(movimiento.fecha, Modifier.weight(1f))
        CeldaTexto(movimiento.descripcion, Modifier.weight(2f))
        CeldaTexto(movimiento.monto.toString(), Modifier.weight(1f))
    }
}

@Composable
fun CeldaTexto(texto: String, modifier: Modifier = Modifier) {
    Text(
        text = texto,
        fontSize = 10.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
            .padding(8.dp)
            .border(BorderStroke(1.dp, Color.Gray))
            .padding(2.dp)
    )

}
