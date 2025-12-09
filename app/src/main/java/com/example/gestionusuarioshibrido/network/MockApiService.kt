package com.example.gestionusuarioshibrido.network

import com.example.gestionusuarioshibrido.data.remote.RemoteUser
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MockApiService {
    @GET("users2")
    suspend fun getAllUsers(): List<RemoteUser>

    @POST("users2")
    suspend fun createUser(@Body user: RemoteUser): RemoteUser

    @PUT("users2/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: RemoteUser): RemoteUser

    @DELETE("users2/{id}")
    suspend fun deleteUser(@Path("id") id: String)
}
