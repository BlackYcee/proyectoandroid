package com.inacap.colocolo.data

class AuthRepository(private val api: AuthApi) {

    suspend fun register(name: String, email: String, pass: String): Result<RegisterResponse> {
        return try {
            val resp = api.register(RegisterRequest(name, email, pass))
            Result.success(resp)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, pass: String): Result<LoginResponse> {
        return try {
            val resp = api.login(LoginRequest(email, pass))
            Result.success(resp)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProfile(token: String): Result<ProfileResponse> {
        return try {
            val resp = api.getProfile("Bearer $token")
            Result.success(resp)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

