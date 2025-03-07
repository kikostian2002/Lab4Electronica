
package edu.unicauca.moneywise.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuardarMovScreen(
    onSave: (Movimiento) -> Unit,
    onCancel: () -> Unit
) {
    val dayState = remember { mutableStateOf("") }
    val monthState = remember { mutableStateOf("") }
    val yearState = remember { mutableStateOf("") }
    val categoriaState = remember { mutableStateOf("") }
    val descripcionState = remember { mutableStateOf("") }
    val montoState = remember { mutableStateOf("") }
    val tipoMovimientoState = remember { mutableStateOf("Ingreso") }
    val expanded = remember { mutableStateOf(false) }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp), // Reducción del espaciado
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Guardar Movimiento",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = white
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .background(
                        color = greenAccent,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(vertical = 12.dp, horizontal = 24.dp)
            )

            // Formulario
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                TextField(
                    value = dayState.value,
                    onValueChange = { dayState.value = it },
                    label = { Text("Día") },
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = monthState.value,
                    onValueChange = { monthState.value = it },
                    label = { Text("Mes") },
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = yearState.value,
                    onValueChange = { yearState.value = it },
                    label = { Text("Año") },
                    modifier = Modifier.weight(1f)
                )
            }

            // Reducir el espaciado de los campos de texto
            TextField(
                value = categoriaState.value,
                onValueChange = { categoriaState.value = it },
                label = { Text("Categoría") },
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            TextField(
                value = descripcionState.value,
                onValueChange = { descripcionState.value = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            TextField(
                value = montoState.value,
                onValueChange = { montoState.value = it },
                label = { Text("Monto") },
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            // Espaciado menor antes del dropdown
            Spacer(modifier = Modifier.height(8.dp))

            ExposedDropdownMenuBox(
                expanded = expanded.value,
                onExpandedChange = { expanded.value = !expanded.value },
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                TextField(
                    value = tipoMovimientoState.value,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de Movimiento") },
                    trailingIcon = {
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                    },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Ingreso") },
                        onClick = {
                            tipoMovimientoState.value = "Ingreso"
                            expanded.value = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Egreso") },
                        onClick = {
                            tipoMovimientoState.value = "Egreso"
                            expanded.value = false
                        }
                    )
                }
            }

            // Espaciado menor antes de los botones
            Spacer(modifier = Modifier.height(8.dp))

            // Botones
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        val formattedDate = "${dayState.value.padStart(2, '0')}/${monthState.value.padStart(2, '0')}/${yearState.value}"
                        val nuevoMovimiento = Movimiento(
                            id = 0,
                            fecha = formattedDate,
                            categoria = categoriaState.value,
                            descripcion = descripcionState.value,
                            monto = montoState.value.toDouble(),
                            tipoMovimiento = tipoMovimientoState.value
                        )
                        onSave(nuevoMovimiento)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = greenAccent,
                        contentColor = white
                    ),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .height(55.dp)
                        .shadow(12.dp, shape = RoundedCornerShape(30.dp))
                ) {
                    Text("Guardar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = white
                    ),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .height(55.dp)
                        .shadow(12.dp, shape = RoundedCornerShape(30.dp))
                ) {
                    Text("Cancelar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
