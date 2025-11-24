package com.inacap.colocolo.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse
    @POST("auth/register")
    suspend fun register(@Body body: RegisterRequest): RegisterResponse

    @GET("/profile")
    suspend fun getProfile(@Header("Authorization") token: String): ProfileResponse

}
