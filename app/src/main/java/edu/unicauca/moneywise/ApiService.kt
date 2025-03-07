package edu.unicauca.moneywise

import edu.unicauca.moneywise.ui.Movimiento
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    // Obtener la lista de movimientos, pasando el token como encabezado
    @GET("movimientos")
    suspend fun getMovimientos(@Header("Authorization") token: String): List<Movimiento>

    // Actualizar un movimiento, también pasando el token
    @POST("movimientos")
    suspend fun updateMovimiento(
        @Header("Authorization") token: String,
        @Body movimiento: Movimiento
    ): Response<Void>

    @DELETE("movimientos/{id}")
    suspend fun deleteMovimiento(
        @Header("Authorization") token: String,
        @Path("id") id: Long
    ): Response<Void>

    @GET("usuarios")
    suspend fun getUsuario(@Header("Authorization") token: String): Usuario

    @POST("usuarios/save")
    suspend fun saveUsuario( @Body usuario: Usuario): Response<Void>
    @PUT("usuarios")
    suspend fun updateUsuario(
        @Body usuario: Usuario
    ): Response<Void>

}
