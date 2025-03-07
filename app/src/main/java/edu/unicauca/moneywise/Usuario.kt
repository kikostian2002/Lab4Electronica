package edu.unicauca.moneywise

data class Usuario(
    var correo: String = "",
    var contrasena: String = "",
    var telefono: String = "",
    var nombre: String = "",
    var rol: String = "",
    var estadoDeCuenta: Double = 0.00
)

