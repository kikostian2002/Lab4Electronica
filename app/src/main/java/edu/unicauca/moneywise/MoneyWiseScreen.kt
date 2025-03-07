

package edu.unicauca.moneywise

import BalanceScreen
import MoneyWiseViewModel
import SobreNosotrosScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.unicauca.moneywise.ui.CompartirMovViewModel
import edu.unicauca.moneywise.ui.EditMovScreen
import edu.unicauca.moneywise.ui.LoginScreen
import edu.unicauca.moneywise.ui.Movimiento
import edu.unicauca.moneywise.ui.MovimientosScreen
import edu.unicauca.moneywise.ui.CompleteScreen

import edu.unicauca.moneywise.ui.ContactoScreen
import edu.unicauca.moneywise.ui.CreateAccountScreen
import edu.unicauca.moneywise.ui.DetallesMovScreen
import edu.unicauca.moneywise.ui.GuardarMovScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

enum class MoneyWiseScreen(val route: String) {
    Login("login"),
    Luna("NewLogin"),
    Home("home"),
    Movimientos("movimientos"),
    Profile("profile"),
    EditMov("edit_mov/{id}/{fecha}/{categoria}/{descripcion}/{monto}"),

    GuardarMov("guardar_mov"), // Nueva ruta
    DetallesMov("detalles_mov/{id}/{fecha}/{categoria}/{descripcion}/{monto}"), // Añadido el id
    CreateAccount("create_account"),
    Contacto("contacto"),
    SobreNosotros("sobre_nosotros")

}

fun encodeUrlParam(param: String): String {
    return URLEncoder.encode(param, StandardCharsets.UTF_8.toString())
}

@Composable
fun MoneyWiseBottomNavigation(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = { navController.navigate(MoneyWiseScreen.Home.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Movimientos") },
            label = { Text("Movimientos") },
            selected = false,
            onClick = { navController.navigate(MoneyWiseScreen.Movimientos.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = { navController.navigate(MoneyWiseScreen.Profile.route) }
        )
    }
}

@Composable
fun MoneyWiseApp(
    moneyWiseViewModel: MoneyWiseViewModel = viewModel(),
    compartirMovViewModel: CompartirMovViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    var authToken by remember { mutableStateOf("") }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != MoneyWiseScreen.Login.route && currentRoute != MoneyWiseScreen.CreateAccount.route) {
                MoneyWiseBottomNavigation(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MoneyWiseScreen.Login.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(MoneyWiseScreen.Login.route) {
                LoginScreen(
                    onLoginSuccess = { token ->
                        authToken = token
                        moneyWiseViewModel.setAuthToken(token)
                        navController.navigate(MoneyWiseScreen.Home.route)
                    },
                    onCreateAccount = {
                        navController.navigate(MoneyWiseScreen.CreateAccount.route)
                    }
                )
            }

            composable(MoneyWiseScreen.CreateAccount.route) {
                CreateAccountScreen(
                    onAccountCreated = { newUsuario ->
                        moneyWiseViewModel.saveUsuario(newUsuario)
                        navController.navigate(MoneyWiseScreen.Login.route)
                    }
                )
            }

            composable(MoneyWiseScreen.Home.route) {
               BalanceScreen(moneyWiseViewModel.usuario)
            }

            composable(MoneyWiseScreen.Movimientos.route) {
                MovimientosScreen(


                    moneyWiseViewModel.movimientos,

                    onEditarClicked = {
                            movimiento ->
                        compartirMovViewModel.setMovimiento(movimiento)
                        navController.navigate(MoneyWiseScreen.EditMov.route)


                        }
                        ,
                    onAgregarClicked = {
                        navController.navigate(MoneyWiseScreen.GuardarMov.route)
                    },
                    onDetallesClicked = {
                       movimiento ->
                        compartirMovViewModel.setMovimiento(movimiento)
                        navController.navigate(MoneyWiseScreen.DetallesMov.route)
                    },
                 onEliminarClicked = { movimiento ->
                     moneyWiseViewModel.deleteMovimiento(movimiento)
                    }
                )


            }

            composable(MoneyWiseScreen.Profile.route) {

                CompleteScreen(usuario = moneyWiseViewModel.usuario, navegar = { route ->
                    println("Navigating to $route")
                    navController.navigate(route)
                }, onLogout = {
                    authToken = ""
                    navController.navigate(MoneyWiseScreen.Login.route)
                })

            }
            composable( MoneyWiseScreen.Contacto.route){
               ContactoScreen()


            }
                composable(MoneyWiseScreen.SobreNosotros.route){
               SobreNosotrosScreen()
                }



            composable(
                MoneyWiseScreen.EditMov.route
            ) {

                EditMovScreen(
                    movimiento = compartirMovViewModel.selectedMovimiento.collectAsState().value,
                    onSave = { updatedMovimiento ->
                        moneyWiseViewModel.updateMovimiento(updatedMovimiento)

                        navController.navigate(MoneyWiseScreen.Movimientos.route) {
                            popUpTo(MoneyWiseScreen.Movimientos.route) { inclusive = true }
                        }
                    },
                    onCancel = {
                        navController.navigate(MoneyWiseScreen.Movimientos.route)
                    }
                )
            }


            composable(MoneyWiseScreen.GuardarMov.route) {
                GuardarMovScreen(
                    onSave = { nuevoMovimiento ->

                        moneyWiseViewModel.saveMovimiento(nuevoMovimiento)

                        navController.navigate(MoneyWiseScreen.Movimientos.route) {
                            popUpTo(MoneyWiseScreen.Movimientos.route) { inclusive = true }
                        }
                    },
                    onCancel = {
                        navController.navigate(MoneyWiseScreen.Movimientos.route)
                    },


                )
            }


            composable(
                MoneyWiseScreen.DetallesMov.route
            ) {

                DetallesMovScreen(
                    movimiento = compartirMovViewModel.selectedMovimiento.collectAsState().value,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
                

            }
        }
    }

}

