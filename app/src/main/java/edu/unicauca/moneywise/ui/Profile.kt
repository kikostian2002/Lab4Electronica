package edu.unicauca.moneywise.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.moneywise.R
import edu.unicauca.moneywise.Usuario

@Composable
fun ConfigurationScreen(usuario: Usuario, logout: () -> Unit = {}, navegar: (String) -> Unit = {}) {
    val imageUri = rememberSaveable { mutableStateOf("") }

    // Color de fondo sólido
    val backgroundColor = colorResource(id = R.color.background_color)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
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
                .padding(top = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(120.dp) // Tamaño del ícono
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF4CAF50).copy(alpha = 0.6f),
                                Color(0xFF81C784).copy(alpha = 0.3f)
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(120f, 120f)
                        ),
                        shape = CircleShape
                    )
                    .padding(4.dp)
                    .shadow(8.dp, CircleShape)
                    .background(Color.White, CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.LightGray.copy(alpha = 0.2f), CircleShape)
                )
            }

            Spacer(Modifier.height(8.dp))
            Text(
                text = usuario.nombre,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 20.sp),
                onTextLayout = {}
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = usuario.correo,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 20.sp),
                onTextLayout = {}
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = logout) {
                Text("Cerrar Sesión")
            }
            Spacer(Modifier.height(16.dp))


            Preferences(onNavigate = { navegar(it) })
        }
    }
}

@Composable
fun CompleteScreen(usuario: Usuario, navegar: (String) -> Unit = {}, onLogout: () -> Unit = {}) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column {
            ConfigurationScreen(usuario, logout = { onLogout() }, navegar={ navegar(it) })
        }
    }
}

@Preview
@Composable
private fun PreviewConfigurationScreen() {
    CompleteScreen(Usuario("Juan Perez", "juan.perez@example.com", "", "", ""))
}
