package edu.unicauca.moneywise.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.moneywise.MoneyWiseScreen
import edu.unicauca.moneywise.R

@Composable
fun Preferences(
    onNavigate: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.background_color))
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .padding(top = 10.dp)
        ) {
            Text(
                text = "Soporte",
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 20.sp),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Tarjetas(
                icon = R.drawable.ic_whatsapp,
                mainText = "Contactos",
                onClick = {
                    println("Navigating to Contacto")
                    onNavigate(MoneyWiseScreen.Contacto.route) }
            )

            Tarjetas(
                icon = R.drawable.ic_feedback,
                mainText = "Feedback"
            )

            Tarjetas(
                icon = R.drawable.ic_privacy_policy,
                mainText = "Política de privacidad"
            )

            Tarjetas(
                icon = R.drawable.ic_about,
                mainText = "Sobre nosotros",
                onClick = {
                    onNavigate(MoneyWiseScreen.SobreNosotros.route) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tarjetas(
    icon: Int,
    mainText: String,
    onClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card_color) // Color de tarjeta
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .padding(top = 10.dp, bottom = 8.dp)
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(12.dp)),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(colorResource(id = R.color.background_color_light))
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp),
                        tint = colorResource(id = R.color.icon_color) // Color del icono
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Column {
                    Text(
                        text = mainText,
                        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 16.sp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = colorResource(id = R.color.primary_text_color) // Color del icono
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTarjetas() {
    Preferences()
}
