package com.example.gestionusuarioshibrido.data

import android.content.Context
import com.example.gestionusuarioshibrido.data.local.UserDatabase
import com.example.gestionusuarioshibrido.network.MockApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val userRepository: UserRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    private val baseUrl = "https://6956653bb9b81bad7af2e397.mockapi.io/api/pud3/"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        coerceInputValues = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: MockApiService by lazy {
        retrofit.create(MockApiService::class.java)
    }

    private val database: UserDatabase by lazy {
        UserDatabase.getDatabase(context)
    }

    override val userRepository: UserRepository by lazy {
        DefaultUserRepository(database.userDao(), retrofitService)
    }

}