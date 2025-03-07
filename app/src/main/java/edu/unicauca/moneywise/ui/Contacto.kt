package edu.unicauca.moneywise.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.graphics.nativeCanvas

@Composable
fun ContactoScreen() {

    val greenPrimary = Color(0xFF1B5E20)
    val greenSecondary = Color(0xFF81C784)
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

        Canvas(modifier = Modifier.fillMaxSize()) {

            drawCircle(
                color = Color.White.copy(alpha = 0.1f),
                radius = 100f,
                center = Offset(80f, 300f)
            )
            drawCircle(
                color = Color.White.copy(alpha = 0.08f),
                radius = 70f,
                center = Offset(300f, 600f)
            )


            drawRoundRect(
                color = Color.White.copy(alpha = 0.06f),
                topLeft = Offset(200f, 800f),
                size = androidx.compose.ui.geometry.Size(100f, 200f),
                cornerRadius = CornerRadius(20f)
            )


            drawContext.canvas.nativeCanvas.drawText(
                "$", 150f, size.height - 150f, android.graphics.Paint().apply {
                    textSize = 150f
                    color = android.graphics.Color.argb(50, 255, 255, 255)
                }
            )

            drawContext.canvas.nativeCanvas.drawText(
                "$", 350f, size.height - 250f, android.graphics.Paint().apply {
                    textSize = 180f
                    color = android.graphics.Color.argb(40, 255, 255, 255)
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "Contacto",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Información de contacto
            ContactCard(
                nombre = "Cristian Bolaños",
                email = "cristianfbolanos@unicauca.edu.co",
                telefono = "3175001488"
            )
            Spacer(modifier = Modifier.height(16.dp))
            ContactCard(
                nombre = "Jhon Diaz",
                email = "jhonsdiaz@unicauca.edu.co",
                telefono = "3128273902"
            )
            Spacer(modifier = Modifier.height(16.dp))
            ContactCard(
                nombre = "Ricardo Diaz",
                email = "radiaz@unicauca.edu.co",
                telefono = "3023283249"
            )
        }
    }
}

@Composable
fun ContactCard(nombre: String, email: String, telefono: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clickable { expanded = !expanded }
            .padding(vertical = 8.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp)), // Sombra más pronunciada
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF709B81)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = nombre,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                ),
                textAlign = TextAlign.Center
            )

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Correo: $email",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp, color = Color.White)
                    )
                    Text(
                        text = "Teléfono: $telefono",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp, color = Color.White)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactoScreenPreview() {
    ContactoScreen()
}
