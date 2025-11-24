package com.inacap.colocolo.data

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val success: Boolean,
    val message: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val token: String?,   // o lo que tu backend devuelva
    val message: String
)

data class ProfileResponse(
    val id: Int,
    val name: String,
    val email: String
)

