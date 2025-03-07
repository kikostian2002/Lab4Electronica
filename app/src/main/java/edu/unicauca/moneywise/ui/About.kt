import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.moneywise.R

@Composable
fun SobreNosotrosScreen() {

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
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo de Moneywise",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp)
            )

            // Título
            Text(
                text = "Sobre Nosotros",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Texto descriptivo
            Text(
                text = "Somos estudiantes de Ingeniería Electrónica y Telecomunicaciones de la Universidad del Cauca, énfasis en Telemática. Esta aplicación fue desarrollada como parte de la Electiva de Aplicaciones Móviles. MoneyWise es una aplicación de control financiero, con la cual podrás controlar tus gastos e ingresos.",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp, color = Color.White),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )

            // Tarjetas de información
            CrearInfoCard(nombre = "Cristian Bolaños")
            Spacer(modifier = Modifier.height(16.dp))
            CrearInfoCard(nombre = "Jhon Diaz")
            Spacer(modifier = Modifier.height(16.dp))
            CrearInfoCard(nombre = "Ricardo Diaz")
        }
    }
}

@Composable
fun CrearInfoCard(nombre: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(80.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp)), // Sombra más pronunciada
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF709B81)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SobreNosotrosScreenPreview() {
    SobreNosotrosScreen()
}
