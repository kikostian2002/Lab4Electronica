import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.moneywise.R
import edu.unicauca.moneywise.Usuario

@Composable
fun BalanceScreen(
    usuario: Usuario
) {
    var estadoDeCuenta by remember { mutableStateOf(0.00) }
    var showDialog by remember { mutableStateOf(false) }

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

        Canvas(modifier = Modifier.fillMaxSize()) {

            drawCircle(
                color = Color.White.copy(alpha = 0.1f),
                radius = 120f,
                center = Offset(100f, 200f)
            )
            drawCircle(
                color = Color.White.copy(alpha = 0.08f),
                radius = 80f,
                center = Offset(300f, 700f)
            )


            drawRoundRect(
                color = Color.White.copy(alpha = 0.08f),
                topLeft = Offset(50f, 600f),
                size = androidx.compose.ui.geometry.Size(100f, 400f),
                cornerRadius = CornerRadius(20f)
            )

            drawRoundRect(
                color = Color.White.copy(alpha = 0.05f),
                topLeft = Offset(220f, 400f),
                size = androidx.compose.ui.geometry.Size(80f, 300f),
                cornerRadius = CornerRadius(16f)
            )


            drawContext.canvas.nativeCanvas.drawText(
                "$", 120f, size.height - 200f, android.graphics.Paint().apply {
                    textSize = 150f
                    color = android.graphics.Color.argb(50, 255, 255, 255)
                }
            )

            drawContext.canvas.nativeCanvas.drawText(
                "$", 350f, size.height - 250f, android.graphics.Paint().apply {
                    textSize = 180f
                    color = android.graphics.Color.argb(45, 255, 255, 255)
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "MoneyWise",
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

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo MoneyWise",
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .background(white)
                    .shadow(8.dp, CircleShape)
                    .padding(20.dp)
            )

            Text(
                text = "Bienvenido, ${usuario.nombre}!",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = white
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            InfoCard(title = "Balance total", amount = "$${usuario.estadoDeCuenta}", greenPrimary, greenAccent)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = greenAccent,
                    contentColor = white
                ),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(55.dp)
                    .shadow(12.dp, shape = RoundedCornerShape(30.dp))
            ) {
                Text(text = "Ver detalles", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun InfoCard(title: String, amount: String, greenPrimary: Color, greenAccent: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(140.dp)
            .padding(vertical = 12.dp)
            .shadow(10.dp, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(2.dp, greenPrimary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = greenPrimary
                ),
                textAlign = TextAlign.Center
            )
            Divider(
                color = greenPrimary,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = amount,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = greenAccent
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBalanceScreen() {
    val usuarioEjemplo = Usuario(nombre = "Juan Perez", estadoDeCuenta = 1000.50)
    BalanceScreen(usuario = usuarioEjemplo)
}
